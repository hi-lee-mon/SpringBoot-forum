package click.devkshun.forum.controller;

import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.dozermapper.core.Mapper;

import click.devkshun.forum.constant.SessionKeyConst;
import click.devkshun.forum.constant.UrlConst;
import click.devkshun.forum.constant.UserEditMessage;
import click.devkshun.forum.constant.ViewNameConst;
import click.devkshun.forum.constant.db.AuthorityKindEnum;
import click.devkshun.forum.constant.db.UserStatusKindEnum;
import click.devkshun.forum.dto.UserEditInfo;
import click.devkshun.forum.dto.UserUpdateInfo;
import click.devkshun.forum.entity.UserInfo;
import click.devkshun.forum.form.UserEditForm;
import click.devkshun.forum.service.UserEditService;
import click.devkshun.forum.util.AppUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

/**
 * ユーザー編集画面Controllerクラス
 * 
 * @author shun
 *
 */
@Controller
@RequiredArgsConstructor
public class UserEditController {

	/** ユーザー編集画面Serviceクラス */
	private final UserEditService service;

	/** セッションオブジェクト */
	private final HttpSession session;

	/** Dozer Mapper */
	private final Mapper mapper;

	/** メッセージソース */
	private final MessageSource messageSource;

	/**
	 * 前画面で選択されたログインIDに紐づくユーザー情報を画面に表示します。
	 * 
	 * @param modelAndView モデル
	 * @return 表示画面
	 * @throws Exception 
	 */
	@GetMapping(UrlConst.USER_EDIT)
	public ModelAndView view(ModelAndView modelAndView, UserEditForm form) throws Exception {
		var loginId = (String) session.getAttribute(SessionKeyConst.SELECTED_LOGIN_ID);
		var userInfoOpt = service.searchUserInfo(loginId);
		if (userInfoOpt.isEmpty()) {
			// 排他チェック
			throw new Exception("ログインIDに該当するユーザー情報が見つかりません。");
		}
		// 画面の表示作成
		setupCommonInfo(modelAndView, userInfoOpt.get());
		// 遷移先の指定
		modelAndView.setViewName(ViewNameConst.USER_EDIT);

		return modelAndView;
	}

	/**
	 * 画面の入力情報をもとにユーザー情報を更新します。
	 * 
	 * @param modelAndView モデル
	 * @param form 入力情報
	 * @param user ログインユーザー情報(@AuthenticationPrincipalでユーザ情報を取得できる※Userの型が異なるとDIが失敗するため必ずspringframework.security.core.userdetails.Userを指定する)
	 * @return 表示画面
	 */
	@PostMapping(value = UrlConst.USER_EDIT, params = "update")
	public ModelAndView updateUser(ModelAndView modelAndView, UserEditForm form,@AuthenticationPrincipal User user) {
		// Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserUpdateInfo updateDto = mapper.map(form, UserUpdateInfo.class);
		updateDto.setLoginId((String)session.getAttribute(SessionKeyConst.SELECTED_LOGIN_ID));

		// ログインIDを取得
		updateDto.setUpdateUserId(user.getUsername());

		var updateResult = service.updateUserInfo(updateDto);
		setupCommonInfo(modelAndView, updateResult.getUpdateUserInfo());

		var updateMessage = updateResult.getUpdateMessage();
		modelAndView.addObject("isError", updateMessage == UserEditMessage.FAILED);
		modelAndView.addObject("message", AppUtil.getMessage(messageSource, updateMessage.getMessageId()));
		modelAndView.setViewName(ViewNameConst.USER_EDIT);

		return modelAndView;
	}

	/**
	 * 画面表示に必要な共通項目の設定を行います。
	 * 
	 * @param modelAndView モデル
	 * @param editedForm 入力済みのフォーム情報
	 */
	private void setupCommonInfo(ModelAndView modelAndView, UserInfo editedForm) {
		modelAndView.addObject("userEditForm", mapper.map(editedForm, UserEditForm.class));
		modelAndView.addObject("userEditInfo", mapper.map(editedForm, UserEditInfo.class));
		modelAndView.addObject("userStatusKindOptions", UserStatusKindEnum.values());
		modelAndView.addObject("authorityKindOptions", AuthorityKindEnum.values());
	}

}


