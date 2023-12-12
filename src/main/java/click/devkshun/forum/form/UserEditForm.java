package click.devkshun.forum.form;

import click.devkshun.forum.constant.db.AuthorityKindEnum;
import click.devkshun.forum.constant.db.UserStatusKindEnum;
import lombok.Data;

/**
 * ユーザー編集画面Formクラス
 * 
 * @author shun
 *
 */
@Data
public class UserEditForm {

	/** ログイン失敗状況をリセットするか(リセットするならtrue) */
	private boolean resetsLoginFailure;

	/** アカウント状態種別 */
	private UserStatusKindEnum userStatusKind;

	/** ユーザー権限種別 */
	private AuthorityKindEnum authorityKind;

}
