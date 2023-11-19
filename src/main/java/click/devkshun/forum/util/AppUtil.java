package click.devkshun.forum.util;

import java.util.Locale;

import org.springframework.context.MessageSource;

/**
 * アプリケーション共通クラス
 *
 * @author shun
 */
public class AppUtil {

  /**
   * 日本語メッセージ取得処理
   *
   * @param messageSource メッセージソース
   * @param key メッセージ キー
   * @param params 置換文字列
   * @return メッセージ
   */
  public static String getMessage(MessageSource messageSource, String key, Object...params){
    return messageSource.getMessage(key,params, Locale.JAPAN);
  }

  /**
   * 汎用メッセージ取得処理
   * @param messageSource メッセージソース
   * @param key メッセージ キー
   * @param locale 言語種別
   * @param params 置換文字列
   * @return メッセージ
   */
  public static String getMessage(MessageSource messageSource, String key,Locale locale, Object...params){
    return messageSource.getMessage(key,params, locale);
  }

  /**
	 * DBのLIKE検索用に、パラメーターにワイルドカードを付与します。
	 * 
	 * @param param パラメーター
	 * @return 前後にワイルドカードが付いたパラメーター
	 */
	public static String addWildcard(String param) {
		return "%" + param + "%";
	}

  /**
   * nullチェックを行います。
   * 
   * @param obj 任意のオブジェクト
   * @return 引数がnullの場合はtrue、それ以外はfalse
   */
  public static boolean isNull(Object obj){
    return obj == null;
  }
}
