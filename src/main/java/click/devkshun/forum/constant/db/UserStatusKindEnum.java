package click.devkshun.forum.constant.db;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ユーザー状態種別
 *
 * @author shun
 */
@Getter
@AllArgsConstructor
public enum UserStatusKindEnum {
  /* 利用可能 */
  ENABLED(false, "利用可能"),

  /* 利用不可 */
  DISABLED(true, "利用不可");

  /** 利用不可か */
  private final boolean isDisabled;

  /** 画面表示する説明文 */
  private final String displayValue;
}
