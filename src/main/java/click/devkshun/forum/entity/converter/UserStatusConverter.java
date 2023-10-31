package click.devkshun.forum.entity.converter;

import click.devkshun.forum.constant.db.UserStatusKindEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * ユーザー情報 ユーザー状態種別フィールドConverterクラス
 *
 * @author shun
 *
 */
@Converter

public class UserStatusConverter implements  AttributeConverter<UserStatusKindEnum, Boolean>{
  /**
   * 引数で受け取ったユーザー状態種別Enumを、利用可否のbooleanに変換します。
   *
   * @param userStatus ユーザー状態種別Enum
   * @return 引数で受け取ったユーザー状態種別Enumに保管されているboolean
   */
  @Override
  public Boolean convertToDatabaseColumn(UserStatusKindEnum userStatus) {
    return userStatus.isDisabled();
  }

  /**
   * 引数で受け取った権限種別のコード値を、ユーザー状態種別Enumに変換します。
   *
   * @param isDisabled 利用可否(利用不可ならtrue)
   * @return 引数で受け取った利用可否から逆引きしたユーザー状態種別Enum
   */
  @Override
  public UserStatusKindEnum convertToEntityAttribute(Boolean isDisabled) {
    return isDisabled ? UserStatusKindEnum.DISABLED : UserStatusKindEnum.ENABLED;
  }

}
