package click.devkshun.forum.controller;

import click.devkshun.forum.constant.UrlConst;
import click.devkshun.forum.constant.db.AuthorityKindEnum;
import click.devkshun.forum.constant.db.UserStatusKindEnum;
import click.devkshun.forum.form.UserListForm;
import click.devkshun.forum.service.UserListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

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
  private static final String KEY_USERLIST = "userList";

  /** モデルキー：ユーザー情報リスト */
  private static final String KEY_USER_STATUS_KINDS = "userStatusKinds";

  /** モデルキー：ユーザー情報リスト */
  private static final String KEY_AUTHORITY_KINDS = "authorityKinds";

  /**
   * 画面の初期表示を行います。
   *
   * <p>またその際、画面選択項目「アカウント状態」「所有権限」の選択肢を生成して渡します。
   *
   * @param modelAndView モデル
   * @return 表示画面
   */
  @GetMapping(UrlConst.USER_LIST)
  public ModelAndView view(ModelAndView modelAndView, UserListForm form) {
    var userInfoDtoList = service.getAllUserList();
    modelAndView.addObject(KEY_USERLIST, userInfoDtoList);

    modelAndView.addObject(KEY_USER_STATUS_KINDS, UserStatusKindEnum.values());
    modelAndView.addObject(KEY_AUTHORITY_KINDS, AuthorityKindEnum.values());

    modelAndView.setViewName(UrlConst.USER_LIST);

    return modelAndView;
  }

}