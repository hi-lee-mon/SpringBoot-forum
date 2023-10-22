package click.devkshun.forum.service;

import click.devkshun.forum.constant.AuthorityKindEnum;
import click.devkshun.forum.entity.UserInfo;
import click.devkshun.forum.form.SignupForm;
import click.devkshun.forum.repository.UserInfoRepository;
import java.util.Optional;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * ユーザ登録画面 Service実装クラス
 *
 * @author shun
 *
 */
@Service
public class SignupServiceImpl implements SignupService {

  /**ユーザ情報テーブルDAO*/
  private final UserInfoRepository repository;
  /**Dozer Mapper*/
  private final Mapper dozerMapper;
  /**パスワードエンコーダー*/
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public SignupServiceImpl(UserInfoRepository repository,Mapper dozerMapper,PasswordEncoder passwordEncoder){
    this.dozerMapper = dozerMapper;
    this.repository = repository;
    this.passwordEncoder = passwordEncoder;
  }

  /**
   * {@inheritDoc}
   */
  @Override
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
