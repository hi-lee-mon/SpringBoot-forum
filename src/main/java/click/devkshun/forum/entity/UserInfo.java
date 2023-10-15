package click.devkshun.forum.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * ユーザ情報テーブル Entity
 *
 * @author shun
 *
 */
@Entity
@Table(name="user_info")
@Data
public class UserInfo {

  /**ログインID*/
  @Id
  @Column(name="login_id")
  private  String loginId;
  private  String password;

  /**パスワード*/
  public UserInfo() {
    // デフォルトコンストラクタの実装
  }
}
