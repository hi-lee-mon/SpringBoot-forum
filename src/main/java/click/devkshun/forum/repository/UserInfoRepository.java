package click.devkshun.forum.repository;

import click.devkshun.forum.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ユーザ情報テーブルDAO
 *
 * @author shun
 *
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,String> {

}
