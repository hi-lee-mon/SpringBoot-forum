package click.devkshun.forum.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import click.devkshun.forum.constant.UserEditMessage;
import click.devkshun.forum.dto.UserEditResult;
import click.devkshun.forum.dto.UserUpdateInfo;
import click.devkshun.forum.entity.UserInfo;
import click.devkshun.forum.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;

/**
 * ユーザー編集画面Service実装クラス
 * 
 * @author ys-fj
 *
 */
@Service
@RequiredArgsConstructor
public class UserEditServiceImpl implements UserEditService {

	/** ユーザー情報テーブルRepository */
	private final UserInfoRepository repository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<UserInfo> searchUserInfo(String loginId) {
		return repository.findById(loginId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserEditResult updateUserInfo(UserUpdateInfo userUpdateInfo) {
		var userUpdateResult = new UserEditResult();

		// 現在の登録情報を取得
		var updateInfoOpt = repository.findById(userUpdateInfo.getLoginId());
		if (updateInfoOpt.isEmpty()) {
			userUpdateResult.setUpdateMessage(UserEditMessage.FAILED);
			return userUpdateResult;
		}

		// 画面の入力情報と現在の登録情報をマージ
		var updateInfo = updateInfoOpt.get();
		updateInfo.setUserStatusKind(userUpdateInfo.getUserStatusKind());
		updateInfo.setAuthorityKind(userUpdateInfo.getAuthorityKind());
		if (userUpdateInfo.isResetsLoginFailure()) {
			updateInfo.setLoginFailureCount(0);
			updateInfo.setAccountLockedTime(null);
		}
		updateInfo.setUpdateTime(LocalDateTime.now());
		updateInfo.setUpdateUser(userUpdateInfo.getUpdateUserId());

		try {
      // 更新処理を実行
			repository.save(updateInfo);
		} catch (Exception e) {
			userUpdateResult.setUpdateMessage(UserEditMessage.FAILED);
			return userUpdateResult;
		}

		userUpdateResult.setUpdateUserInfo(updateInfo);
		userUpdateResult.setUpdateMessage(UserEditMessage.SUCCEED);
		return userUpdateResult;
	}

}
