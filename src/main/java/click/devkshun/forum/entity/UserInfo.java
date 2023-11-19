package click.devkshun.forum.entity;

import java.time.LocalDateTime;

import click.devkshun.forum.constant.db.AuthorityKindEnum;
import click.devkshun.forum.constant.db.UserStatusKindEnum;
import click.devkshun.forum.entity.converter.UserAuthorityConverter;
import click.devkshun.forum.entity.converter.UserStatusConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class UserInfo {

  /** ログインID */
  @Id
  @Column(name = "login_id")
  private String loginId;

  /** パスワード */
  private String password;

  /** ログイン失敗回数 */
  @Column(name = "login_failure_count")
  private int loginFailureCount = 0;

  /** アカウントロック日時 */
  @Column(name = "account_locked_time")
  private LocalDateTime accountLockedTime;

  /** ユーザー状態種別 */
  @Column(name = "is_disabled")
  @Convert(converter = UserStatusConverter.class)
  private UserStatusKindEnum userStatusKind;

  /** ユーザー権限種別 */
  @Column(name = "authority")
  @Convert(converter = UserAuthorityConverter.class)
  private AuthorityKindEnum authorityKind;

  @Column(name = "create_time")
  private LocalDateTime createTime;

  @Column(name = "update_time")
  private LocalDateTime updateTime;

  @Column(name = "update_user")
  private String updateUser;

  /**
   * デフォルトコンストラクタ
   */
  public UserInfo() {
  }

  /**
   * ログイン失敗回数をインクリメントします。
   *
   * @return ログイン失敗回数がインクリメントされた、自身のインスタンス
   */
  public UserInfo incrementLoginFailureCount() {
    return new UserInfo(loginId, password, ++loginFailureCount, accountLockedTime, userStatusKind, authorityKind ,createTime, updateTime, updateUser);
  }

  /**
   * ログイン失敗情報をリセットします。
   *
   * @return ログイン失敗情報がリセットされた、自身のインスタンス
   */
  public UserInfo resetLoginFailureInfo() {
    return new UserInfo(loginId, password, 0, null, userStatusKind, authorityKind, createTime, updateTime, updateUser);
  }

  /**
   * ログイン失敗回数、アカウントロック日時を更新し、アカウントロック状態に更新します。
   *
   * @return ログイン失敗回数、アカウントロック日時が更新された、自身のインスタンス
   */
  public UserInfo updateAccountLocked() {
    return new UserInfo(loginId, password, 0, LocalDateTime.now(), userStatusKind, authorityKind, createTime, updateTime, updateUser);
  }
}
