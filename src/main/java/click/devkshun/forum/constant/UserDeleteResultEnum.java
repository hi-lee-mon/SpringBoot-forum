package click.devkshun.forum.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 処理結果種別
 * 
 * @author shun
 * 
 */
@Getter
@AllArgsConstructor
public enum UserDeleteResultEnum {

	/* エラーなし */
	SUCCEED(MessageConst.USERLIST_DELETE_SUCCEED),

	/* エラーあり */
	ERROR(MessageConst.USERLIST_NON_EXISTED_LOGIN_ID);

	/** メッセージID */
	private String messageId;
}
