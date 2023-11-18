package click.devkshun.forum.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.github.dozermapper.core.Mapper;

import click.devkshun.forum.dto.UserInfoDto;
import click.devkshun.forum.entity.UserInfo;
import click.devkshun.forum.repository.UserInfoRepository;
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
      userInfoDto.setStatus(gotUserInfo.getStatus().getDisplayValue());
      userInfoDto.setAuthority(gotUserInfo.getAuthority().getDisplayValue());
      userInfoDtoList.add(userInfoDto);
    }

    return userInfoDtoList;
  }

}
