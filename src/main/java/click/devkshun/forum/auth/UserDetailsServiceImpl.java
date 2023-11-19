package click.devkshun.forum.auth;

import click.devkshun.forum.entity.UserInfo;
import click.devkshun.forum.repository.UserInfoRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * DBのユーザ情報取得とアプリ内ユーザ情報の生成
 *
 * @author shun
 */
@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
  /**ユーザ情報テーブルrepository*/
  private final UserInfoRepository repository;

  /** アカウントロックを行うログイン失敗回数境界値 */
  @Value("${security.locking-border-count}")
  private int lockingBorderCount;

  /** アカウントロックの継続時間 */
  @Value("${security.locking-time}")
  private int lockingTime;

  /**
   * ユーザ情報生成
   * @param loginId ログインID (設定でusernameからloginIdに変更している)
   * @throws UsernameNotFoundException
   */
  @Override
  public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
    Optional<UserInfo> userInfoOpt = repository.findById(loginId);
    UserInfo userInfo = userInfoOpt.orElseThrow(()->
        new UsernameNotFoundException(loginId));

    var accountLockedTime = userInfo.getAccountLockedTime();
    var isAccountLocked = accountLockedTime != null
          && accountLockedTime.plusHours(lockingTime).isAfter(LocalDateTime.now());

    // ユーザ情報の生成を行う処理
    // User.withUsernameのUserクラスがUserDetailsの実装クラス
    // Userを使って自分のUserDetailsを作成する
    // 作成したUserDetailsは後続の処理でフィールドをチェックされる
    // チェック結果がNGの場合はEventListenerが発火して処理を行う？
    // チェック結果OKの場合はBeanをまるごとセッションとして保管する処理が実行される。ログイン後はそのBeanを確認することでユーザ情報を画面に出したりできる。
    return User.withUsername(userInfo.getLoginId())
        .password(userInfo.getPassword())
//        .roles("USER")
        .authorities(userInfo.getAuthorityKind().getCode())
        .disabled(userInfo.getUserStatusKind().isDisabled())
        .accountLocked(isAccountLocked)
        // .accountExpired(true) ※アカウント有効期限切れか？
        // .credentialsExpired(true) ※パスワード有効期限切れか？
        .build();
  }

  /**
   * 認証失敗時にログイン失敗回数を加算、ロック日時を更新する
   *
   * @param event イベント情報
   */
  @EventListener
  public void handle(AuthenticationFailureBadCredentialsEvent event) {
    // AuthenticationFailureBadCredentialsのイベントが発生した場合実行される
    // このイベントはログインIDやパスワードを間違えたときにSpring Securityが投げる例外

    // イベントにはユーザIDがあるので取得する
    var loginId = event.getAuthentication().getName();
    repository.findById(loginId).ifPresent(userInfo -> {
      // 入力されたログインIDが存在するものであれば、DBのデータを更新する
      repository.save(userInfo.incrementLoginFailureCount());
      var isReachFailureCount = userInfo.getLoginFailureCount() == lockingBorderCount;
      // アカウントロックの規定値に達した場合はアカウントロック処理を実行
      if (isReachFailureCount) {
        repository.save(userInfo.updateAccountLocked());
      }
    });
    // 一致するログインIDが存在しないため処理なし。画面上にエラーメッセージを出力するだけ。
  }

  /**
   * 認証成功時にログイン失敗回数をリセットする
   *
   * @param event イベント情報
   */
  @EventListener
  public void handle(AuthenticationSuccessEvent event) {
    // AuthenticationFailureBadCredentialsのイベントが発生した場合実行される
    var loginId = event.getAuthentication().getName();
    repository.findById(loginId).ifPresent(userInfo -> {
      repository.save(userInfo.resetLoginFailureInfo());
    });
  }

}
