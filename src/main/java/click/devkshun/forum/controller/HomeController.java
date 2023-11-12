package click.devkshun.forum.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import click.devkshun.forum.constant.UrlConst;
import click.devkshun.forum.constant.db.AuthorityKindEnum;

/**
 * ホーム画面 Controller
 *
 * @author shun
 *
 */
@Controller
public class HomeController {

  /**
   * 初期表示
   *
   * @return 表示画面
   */
  @GetMapping(UrlConst.HOME)
  public ModelAndView view(@AuthenticationPrincipal User user,ModelAndView modelAndView){
    // 管理者権限を持つユーザ情報か判定する
    var hasUserManageAuth = user.getAuthorities().stream().allMatch(authority -> authority.getAuthority().equals(
        AuthorityKindEnum.ITEM_AND_USER_MANAGER.getCode()));
    // 遷移先の指定
    modelAndView.setViewName(UrlConst.HOME);
    // 管理者権限フラグ
    modelAndView.addObject("hasUserManageAuth", hasUserManageAuth);
    return modelAndView;
  }

}
