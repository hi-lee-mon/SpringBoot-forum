package click.devkshun.forum.dto;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * ユーザー一覧画面DTOクラス
 *
 * @author shun
 *
 */
@Data
public class UserInfoDto {

  /** ログインID */
  private String loginId;

  /** ログイン失敗回数 */
  private int loginFailureCount;

  /** アカウントロック日時 */
  private LocalDateTime accountLockedTime;

  /** アカウント状態 */
  private String status;

  /** 権限 */
  private String authority;

}
