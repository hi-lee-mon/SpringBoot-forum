package click.devkshun.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import click.devkshun.forum.entity.UserInfo;

/**
 * ユーザ情報テーブルDAO
 *
 * @author shun
 *
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,String> {

}
