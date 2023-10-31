package click.devkshun.forum.service;

import click.devkshun.forum.dto.UserInfoDto;
import java.util.List;
/**
 * ユーザー一覧画面Serviceクラス
 *
 * @author shun
 *
 */
public interface UserListService {
  /**
   * ユーザー情報テーブルを全件検索し、ユーザー一覧画面に必要な情報へ変換して返却します。
   *
   * @return ユーザー情報テーブルの全登録情報
   */
  public List<UserInfoDto>  getAllUserList();
}
