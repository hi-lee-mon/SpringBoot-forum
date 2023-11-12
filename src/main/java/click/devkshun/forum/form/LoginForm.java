package click.devkshun.forum.form;


import lombok.Data;

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
