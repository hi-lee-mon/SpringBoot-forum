package click.devkshun.forum.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import click.devkshun.forum.entity.UserInfo;
import click.devkshun.forum.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;

/**
 * ログイン画面 Service
 *
 * @author shun
 *
 */
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

  /**ユーザ情報テーブルDAO*/
  private final UserInfoRepository repository;

  /**
   * ユーザ情報テーブル 主キー検索
   * @param loginId ログインID
   * @return ユーザ情報テーブルを主キー検索した結果(1件)
   */
  public Optional<UserInfo> searchUserById(String loginId){
    return repository.findById(loginId);
  }
}
