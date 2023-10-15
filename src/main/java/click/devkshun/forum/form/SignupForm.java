package click.devkshun.forum.form;


import click.devkshun.forum.constant.ValidationMessageConst;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * ユーザ登録画面 Form
 *
 * @author shun
 *
 */
@Data
public class SignupForm {

  /**ログインID*/
  @Length(min=8,max=20,message = ValidationMessageConst.LOGIN_ID_OVER)
  private String loginId;

  /**パスワード*/
  @Length(min=8,max=20)
  private String password;
}
