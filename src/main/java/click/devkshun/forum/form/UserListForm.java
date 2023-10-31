package click.devkshun.forum.form;


import click.devkshun.forum.constant.db.AuthorityKindEnum;
import click.devkshun.forum.constant.db.UserStatusKindEnum;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * ユーザー登録画面Formクラス
 *
 * @author ys-fj
 *
 */
@Data
public class UserListForm {
  /** ログインID */
  @Length(min = 8, max = 20)
  private String loginId;

  /** アカウント状態種別 */
  private UserStatusKindEnum userStatusKind;

  /** ユーザー権限種別 */
  private AuthorityKindEnum authorityKind;

  /** ユーザー一覧情報から選択されたログインID */
  private String selectedLoginId;

  /**
   * ユーザー一覧情報から選択されたログインIDをクリアします。
   *
   * @return ユーザー一覧情報から選択されたログインIDクリア後のインスタンス
   */
  public UserListForm clearSelectedLoginId() {
    this.selectedLoginId = null;

    return this;
  }
}
