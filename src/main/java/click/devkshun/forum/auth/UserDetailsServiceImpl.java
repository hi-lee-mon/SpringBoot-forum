package click.devkshun.forum.auth;

import click.devkshun.forum.entity.UserInfo;
import click.devkshun.forum.repository.UserInfoRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
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

    // ユーザ情報の生成を行う。
    // UserクラスがUser情報のBean情報
    // 作成したUserクラスは後続の処理でフィールドをチェックされる
    // チェック結果OKの場合はBeanをまるごとセッションとして保管される。
    return User.withUsername(userInfo.getLoginId())
        .password(userInfo.getPassword())
        .build();
  }
}
