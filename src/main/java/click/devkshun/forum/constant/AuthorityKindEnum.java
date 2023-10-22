package click.devkshun.forum.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ユーザー権限種別
 *
 * @author shun
 */
@Getter
@AllArgsConstructor
public enum AuthorityKindEnum {
  ITEM_WATCHER("1"),
  ITEM_MANAGER("2"),
  ITEM_AND_USER_MANAGER("3");

  private final String AuthorityKind;
}
