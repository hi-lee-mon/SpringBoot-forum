package click.devkshun.forum.constant;

/**
 * URL定数クラス
 *
 * @author shun
 */
public class UrlConst {
  /**ホーム画面*/
  public static final String HOME = "/index";
  /**ログイン画面*/
  public static final String LOGIN = "/login";
  /**ユーザ登録画面*/
  public static final String SIGNUP = "/signup";
  /**ユーザー一覧画面 */
  public static final String USER_LIST = "/userList";
  /**エラー画面 */
  public static final String ERROR = "/error";
  /**編集画面 */
  public static final String USER_EDIT = "/userEdit";

  /**認証不要 画面*/
  public static final String[] NO_AUTHENTICATION = {LOGIN,SIGNUP,ERROR,"webjars/**"};
}
