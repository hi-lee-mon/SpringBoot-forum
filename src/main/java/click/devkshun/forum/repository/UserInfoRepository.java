package click.devkshun.forum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import click.devkshun.forum.constant.db.AuthorityKindEnum;
import click.devkshun.forum.constant.db.UserStatusKindEnum;
import click.devkshun.forum.entity.UserInfo;

/**
 * Spring Data JPAの使い方
 * ・メソッド名によってクエリを自動生成することができる。
 * ・メソッド名の命名規則は以下の通り。
 * 　　・findBy＋検索条件の項目名＋検索条件の演算子＋検索条件の項目名
 * 　　  例：findBy＋{LoginId}＋{Like}＋{And}＋{UserStatusKind}
 *          ※検索条件の項目名は、エンティティのフィールド名を指定する。(このファイルではUserInfoエンティティを利用しているため、UserInfoのフィールド名を指定する)
 * 　　・検索条件の演算子は、以下の通り。
 * 　　　　・Is：完全一致
 * 　　　　・Equals：完全一致
 * 　　　　・Like：部分一致
 * 　　　　・GreaterThan：より大きい
 * 　　　　・GreaterThanEqual：以上
 * 　　　　・LessThan：より小さい
 * 　　　　・LessThanEqual：以下
 * 　　　　・Between：範囲指定
 * 　　　　・IsNull：Null
 * 　　　　・IsNotNull：Not Null
 * 　　　　・OrderBy：並び替え
 * 　　　　・Not：否定
 * 　　　　・In：リストの中に含まれる
 * 　　　　・NotIn：リストの中に含まれない
 * 　　　　・StartingWith：前方一致
 * 　　　　・EndingWith：後方一致
 * 　　　　・Containing：部分一致
 * 　　　　・True：True
 * 　　　　・False：False
 * 　　　　・IgnoreCase：大文字小文字を区別しない
 */


/**
 * ユーザ情報テーブルDAO
 *
 * @author shun
 *
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,String> {
	/**
	 * ログインIDの部分一致検索を行います。
	 * 
	 * @param loginId ログインID
	 * @return 検索でヒットしたユーザー情報のリスト
	 */
	List<UserInfo> findByLoginIdLike(String loginId);

	/**
	 * ログインID、アカウント状態の項目を使って検索を行います。
	 * 
	 * <p>■検索条件
	 * <lu>
	 * <li>ログインID：部分一致</li>
	 * <li>アカウント状態：完全一致</li>
	 * </lu>
	 * <p>
	 * 
	 * @param loginId ログインID
	 * @param userStatusKind アカウント状態
	 * @return 検索でヒットしたユーザー情報のリスト
	 */
	List<UserInfo> findByLoginIdLikeAndUserStatusKind(String loginId, UserStatusKindEnum userStatusKind);

	/**
	 * ログインID、権限の項目を使って検索を行います。
	 * 
	 * <p>■検索条件
	 * <lu>
	 * <li>ログインID：部分一致</li>
	 * <li>権限：完全一致</li>
	 * </lu>
	 * <p>
	 * 
	 * @param loginId ログインID
	 * @param authorityKind 権限
	 * @return 検索でヒットしたユーザー情報のリスト
	 */
	List<UserInfo> findByLoginIdLikeAndAuthorityKind(String loginId, AuthorityKindEnum authorityKind);

	/**
	 * ログインID、アカウント状態、権限の項目を使って検索を行います。
	 * 
	 * <p>■検索条件
	 * <lu>
	 * <li>ログインID：部分一致</li>
	 * <li>アカウント状態：完全一致</li>
	 * <li>権限：完全一致</li>
	 * </lu>
	 * <p>
	 * 
	 * @param loginId ログインID
	 * @param userStatusKind アカウント状態
	 * @param authorityKind 権限
	 * @return 検索でヒットしたユーザー情報のリスト
	 */
	List<UserInfo> findByLoginIdLikeAndUserStatusKindAndAuthorityKind(String loginId, UserStatusKindEnum userStatusKind,
			AuthorityKindEnum authorityKind);
}
