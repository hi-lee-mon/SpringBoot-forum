package click.devkshun.forum.form;


import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * ログイン画面 Form
 *
 * @author shun
 *
 */
@Data
public class LoginForm {

  /**ログインID*/
  private String loginId;

  /**パスワード*/
  private String password;
}
