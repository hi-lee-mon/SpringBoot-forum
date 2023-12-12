package click.devkshun.forum.service;

import java.util.List;

import click.devkshun.forum.constant.UserDeleteResultEnum;
import click.devkshun.forum.dto.UserInfoDto;
import click.devkshun.forum.dto.UserSearchInfoDto;
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


  /**
   * ユーザー情報を条件検索した結果を画面の表示用に変換して返却します。
   * @param userSearchInfoDto 検索条件
   * @return 検索結果
   */
  public List<UserInfoDto>  getUserListByParam(UserSearchInfoDto userSearchInfoDto);

  /**
	 * 指定されたIDのユーザー情報を削除します。
	 * 
	 * @param loginId ログインID
	 * @return 実行結果(エラー有無)
	 */
	public UserDeleteResultEnum deleteUserInfoById(String loginId);
}
