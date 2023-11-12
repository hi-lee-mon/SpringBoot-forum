package click.devkshun.forum.service;

import java.util.Optional;

import click.devkshun.forum.entity.UserInfo;

/**
 * ログイン画面 Serviceインターフェース
 *
 * @author shun
 */
public interface LoginService {
  public Optional<UserInfo> searchUserById(String loginId);
}
