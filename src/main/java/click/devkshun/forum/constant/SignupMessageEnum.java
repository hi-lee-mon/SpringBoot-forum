package click.devkshun.forum.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SignupMessageEnum {
  SUCCESS(MessageConst.SIGNUP_SUCCESS,false),
  EXISTS_LOGIN_ID(ErrorMessageConst.SIGNUP_EXISTS_LOGIN_ID,true);

  private String messageId;
  private boolean isError;
}
