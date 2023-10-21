package click.devkshun.forum.service;

import click.devkshun.forum.constant.AuthorityKindEnum;
import click.devkshun.forum.entity.UserInfo;
import click.devkshun.forum.form.SignupForm;
import click.devkshun.forum.repository.UserInfoRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.dozer.Mapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * ユーザ登録画面 Service
 *
 * @author shun
 *
 */
@Service
@RequiredArgsConstructor
public class SignupService {

  /**ユーザ情報テーブルDAO*/
  private final UserInfoRepository repository;
  /**Dozer Mapper*/
  private final Mapper dozerMapper;
  /**パスワードエンコーダー*/
  private final PasswordEncoder passwordEncoder;

  /**
   * 画面の入力情報を元にユーザー情報テーブルの新規登録を行います。
   *
   * <p>ただし、以下のテーブル項目はこの限りではありません。
   * <ul>
   * <li>パスワード：画面で入力したパスワードがハッシュ化され登録されます。</li>
   * <li>権限：常に「商品情報の確認が可能」のコード値が登録されます。</li>
   * </ul>
   *
   * @param signupForm 入力情報
   * @return 登録情報(ユーザー情報Entity)、既に同じユーザIDで登録がある場合はEmpty
   */
  public Optional<UserInfo> registerUserInfo(SignupForm signupForm){
    // ユーザが既に登録済か確認
    Optional<UserInfo> foundUser = repository.findById(signupForm.getLoginId());
    if(foundUser.isPresent()){
      // 登録済の場合はUserInfoが存在しないことを示すためにemptyを返す
      return Optional.empty();
    }
    // UserInfoインスタンスが持つフィールドにsignupFormのデータを流し込む。両方にないフィールドは無視される。
    UserInfo userInfo = dozerMapper.map(signupForm,UserInfo.class);
    String encodedPassword = passwordEncoder.encode(signupForm.getPassword());
    userInfo.setPassword(encodedPassword);
    userInfo.setAuthority(AuthorityKindEnum.ITEM_WATCHER.getAuthorityKind());
    return Optional.of(repository.save(userInfo));
  }
}
