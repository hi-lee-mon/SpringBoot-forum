package click.devkshun.forum.dto;

import click.devkshun.forum.constant.db.AuthorityKindEnum;
import click.devkshun.forum.constant.db.UserStatusKindEnum;
import lombok.Data;

/**
 * ユーザー更新情報DTOクラス
 * 
 * @author shun
 *
 */
@Data
public class UserUpdateInfo {

	/** ログインID */
	private String loginId;

	/** ログイン失敗状況をリセットするか(リセットする場合、true) */
	private boolean resetsLoginFailure;

	/** アカウント状態種別 */
	private UserStatusKindEnum userStatusKind;

	/** ユーザー権限種別 */
	private AuthorityKindEnum authorityKind;

	/** 更新ユーザーID */
	private String updateUserId;
}
