package click.devkshun.forum.dto;

import click.devkshun.forum.constant.UserEditMessage;
import click.devkshun.forum.entity.UserInfo;
import lombok.Data;

/**
 * ユーザー編集結果DTOクラス
 * 
 * @author shun
 *
 */
@Data
public class UserEditResult {

	/** ユーザー更新結果 */
	private UserInfo updateUserInfo;

	/** ユーザー更新結果メッセージEnum */
	private UserEditMessage updateMessage;
}
