package click.devkshun.forum.controller;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.dozermapper.core.Mapper;

import click.devkshun.forum.constant.ExecuteResult;
import click.devkshun.forum.constant.UrlConst;
import click.devkshun.forum.constant.db.AuthorityKindEnum;
import click.devkshun.forum.constant.db.UserStatusKindEnum;
import click.devkshun.forum.dto.UserSearchInfoDto;
import click.devkshun.forum.form.UserListForm;
import click.devkshun.forum.service.UserListService;
import click.devkshun.forum.util.AppUtil;
import lombok.RequiredArgsConstructor;


/**
 * ユーザー一覧画面Controllerクラス
 *
 * @author shun
 *
 */

@Controller
@RequiredArgsConstructor
public class UserListController {
  /** ユーザー一覧画面Serviceクラス */
  private final UserListService service;

  /** モデルキー：ユーザー情報リスト */
  private static final String KEY_USER_LIST = "userList";

  /** モデルキー：ユーザー情報リスト */
  private static final String KEY_USER_STATUS_KINDS = "userStatusKindOptions";

  /** モデルキー：ユーザー情報リスト */
  private static final String KEY_AUTHORITY_KINDS = "authorityOptions";

  /** Dozer Mapper */
  private final Mapper mapper;

  //  /**メッセージソース*/
  private final MessageSource messageSource;

  /**
   * 画面の初期表示を行います。
   *
   * <p>またその際、画面選択項目「アカウント状態」「所有権限」の選択肢を生成して渡します。
   *
   * @param modelAndView モデル
   * @UserListForm form 検索条件フォーム
   * @param form 検索条件フォーム
   * @return 表示画面
   */
  @GetMapping(UrlConst.USER_LIST)
  public ModelAndView view(ModelAndView modelAndView, UserListForm form) {
    // ユーザ一覧情報を取得
    var userInfoDtoList = service.getAllUserList();
    // 検索条件フォームの初期値をセット
    modelAndView.addObject(KEY_USER_STATUS_KINDS, UserStatusKindEnum.values());
    modelAndView.addObject(KEY_AUTHORITY_KINDS, AuthorityKindEnum.values());
    // 選択されているログインIDを初期化
    modelAndView.addObject("selectedLoginId", new String());
    // ユーザ一覧情報をセット
    modelAndView.addObject(KEY_USER_LIST, userInfoDtoList);
    // 遷移先の指定
    modelAndView.setViewName(UrlConst.USER_LIST);

    return modelAndView;
  }

  /**
   * ユーザー情報の条件検索を行い、検索結果を返却します。
   * @param modelAndView モデル
   * @param form 検索条件フォーム
   * @return
   */
  @PostMapping(value = UrlConst.USER_LIST, params="search") // paramsでボタン要素のname属性を指定することで、どのボタンが押されたかを判定できる
  public ModelAndView searchUser(ModelAndView modelAndView, UserListForm form){
		var searchDto = mapper.map(form, UserSearchInfoDto.class);
    // 検索処理
    var userInfoDtoList = service.getUserListByParam(searchDto);

    // 検索フォームをセット
    modelAndView.addObject(KEY_USER_STATUS_KINDS, UserStatusKindEnum.values());
    modelAndView.addObject(KEY_AUTHORITY_KINDS, AuthorityKindEnum.values());
    // 選択されているログインIDを初期化
    modelAndView.addObject("selectedLoginId", new String());
    // ユーザ一覧情報をセット
    modelAndView.addObject(KEY_USER_LIST, userInfoDtoList);

    // 遷移先の指定
    modelAndView.setViewName(UrlConst.USER_LIST);

    return modelAndView;
  }

  /**
	 * 選択行のユーザー情報を削除して、最新情報で画面を再表示します。
	 * 
	 * @param  modelAndView モデル
	 * @param form 検索条件フォーム
	 * @return 表示画面
	 */
	@PostMapping(value = UrlConst.USER_LIST, params = "delete")
	public ModelAndView deleteUser(ModelAndView modelAndView, UserListForm form) {
		ExecuteResult executeResult = service.deleteUserInfoById(form.getSelectedLoginId());
		modelAndView.addObject("isError", executeResult == ExecuteResult.ERROR); // isErrorはboolean
		modelAndView.addObject("message", AppUtil.getMessage(messageSource, executeResult.getMessageId()));

		// 削除後、フォーム情報の「選択されたログインID」は不要になるため、クリアします。
		return searchUser(modelAndView, form.clearSelectedLoginId());
	}

}