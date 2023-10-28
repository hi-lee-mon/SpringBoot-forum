package click.devkshun.forum.constant;

/**
 * URL定数クラス
 *
 * @author shun
 */
public class UrlConst {
  /**ホーム画面*/
  public static final String HOME = "index";
  /**ログイン画面*/
  public static final String LOGIN = "login";
  /**ユーザ登録画面*/
  public static final String SIGNUP = "signup";
  /**認証不要 画面*/
  public static final String[] NO_AUTHENTICATION = {LOGIN,SIGNUP,"webjars/**"};
}
