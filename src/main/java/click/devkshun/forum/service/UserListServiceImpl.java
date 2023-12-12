package click.devkshun.forum.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.github.dozermapper.core.Mapper;

import click.devkshun.forum.constant.UserDeleteResultEnum;
import click.devkshun.forum.dto.UserInfoDto;
import click.devkshun.forum.dto.UserSearchInfoDto;
import click.devkshun.forum.entity.UserInfo;
import click.devkshun.forum.form.UserListForm;
import click.devkshun.forum.repository.UserInfoRepository;
import click.devkshun.forum.util.AppUtil;
import lombok.RequiredArgsConstructor;

/**
 * ユーザー一覧画面Service実装クラス
 *
 * @author shun
 *
 */
@Service
@RequiredArgsConstructor
public class UserListServiceImpl implements UserListService {

  /** ユーザー情報テーブルDAO */
  private final UserInfoRepository repository;

  /** Dozer Mapper */
  private final Mapper mapper;

  /**
   * {@inheritDoc}
   */
  @Override
  public List<UserInfoDto>  getAllUserList() {
    return toUserInfoList(repository.findAll());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<UserInfoDto>  getUserListByParam(UserSearchInfoDto userSearchInfoDto){
    var userInfo = mapper.map(userSearchInfoDto, UserInfo.class);
		return toUserInfoList(findUserInfoByParam(userInfo));
  }

  	/**
	 * ユーザー情報の条件検索を行い、検索結果を返却します。
	 * 
	 * @param form 入力情報
	 * @return 検索結果
	 */
	private List<UserInfo> findUserInfoByParam(UserInfo userInfo) {
		var loginIdParam = AppUtil.addWildcard(userInfo.getLoginId());
    boolean existsStatus = !AppUtil.isNull(userInfo.getUserStatusKind());
    boolean existsAuthority = !AppUtil.isNull(userInfo.getAuthorityKind());

    if(existsStatus && existsAuthority){
      return repository.findByLoginIdLikeAndUserStatusKindAndAuthorityKind(loginIdParam,
					userInfo.getUserStatusKind(), userInfo.getAuthorityKind());
    }
    if(existsStatus){
      return repository.findByLoginIdLikeAndUserStatusKind(loginIdParam, userInfo.getUserStatusKind());
    }
    if(existsAuthority){
      return repository.findByLoginIdLikeAndAuthorityKind(loginIdParam, userInfo.getAuthorityKind());
    }
    // どちらもない場合は全件検索
    return repository.findByLoginIdLike(loginIdParam);
	}

  /**
	 * {@inheritDoc}
	 */
	@Override
	public UserDeleteResultEnum deleteUserInfoById(String loginId) {
		var userInfo = repository.findById(loginId);
		if (userInfo.isEmpty()) {
			return UserDeleteResultEnum.ERROR;
		}

		repository.deleteById(loginId);

		return UserDeleteResultEnum.SUCCEED;
	}

  /**
   * ユーザー情報EntityのListをユーザー一覧情報DTOのListに変換します。
   *
   * @param gotUserInfoList ユーザー情報EntityのList
   * @return ユーザ一覧情報DTOのList
   */
  private List<UserInfoDto> toUserInfoList(List<UserInfo> gotUserInfoList) {
    var userInfoDtoList = new ArrayList<UserInfoDto>();
    for (UserInfo gotUserInfo : gotUserInfoList) {
      // entityをdtoに詰める
      UserInfoDto userInfoDto = mapper.map(gotUserInfo, UserInfoDto.class);
      userInfoDto.setStatus(gotUserInfo.getUserStatusKind().getDisplayValue());
      userInfoDto.setAuthority(gotUserInfo.getAuthorityKind().getDisplayValue());
      userInfoDtoList.add(userInfoDto);
    }

    return userInfoDtoList;
  }

}
