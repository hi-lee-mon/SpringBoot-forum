package click.devkshun.forum.dto;

import click.devkshun.forum.constant.db.AuthorityKindEnum;
import click.devkshun.forum.constant.db.UserStatusKindEnum;
import lombok.Data;

/**
 * ユーザー一覧画面検索用DTOクラス
 * 
 * @author shun
 *
 */
@Data
public class UserSearchInfoDto {

	/** ログインID */
	private String loginId;

	/** アカウント状態種別 */
	private UserStatusKindEnum userStatusKind;

	/** ユーザー権限種別 */
	private AuthorityKindEnum authorityKind;

}
