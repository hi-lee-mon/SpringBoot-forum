package click.devkshun.forum.service;

import click.devkshun.forum.entity.UserInfo;
import java.util.Optional;

/**
 * ログイン画面 Serviceインターフェース
 *
 * @author shun
 */
public interface LoginService {
  public Optional<UserInfo> searchUserById(String loginId);
}
