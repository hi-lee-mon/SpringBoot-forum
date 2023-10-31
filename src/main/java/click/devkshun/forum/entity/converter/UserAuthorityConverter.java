package click.devkshun.forum.entity.converter;


import click.devkshun.forum.constant.db.AuthorityKindEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * ユーザー情報 権限フィールドConverterクラス
 *
 * @author shun
 *
 */
@Converter
public class UserAuthorityConverter implements AttributeConverter<AuthorityKindEnum, String> {
  /**
   * 引数で受け取った権限種別Enumを、権限種別のコード値に変換します。
   *
   * @param authorityKind 権限種別Enum
   * @return 引数で受け取った権限種別Enumに保管されているコード値
   */
  @Override
  public String convertToDatabaseColumn(AuthorityKindEnum authorityKind) {
    return authorityKind.getCode();
  }

  /**
   * 引数で受け取った権限種別のコード値を、権限種別Enumに変換します。
   *
   * @param value 権限種別のコード値
   * @return 引数で受け取った権限種別のコード値から逆引きした権限種別Enum
   */
  @Override
  public AuthorityKindEnum convertToEntityAttribute(String value) {
    return AuthorityKindEnum.from(value);
  }
}
