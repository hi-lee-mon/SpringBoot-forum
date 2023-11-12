package click.devkshun.forum.controller;

import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import click.devkshun.forum.constant.UrlConst;
import click.devkshun.forum.form.LoginForm;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

/**
 * ログイン画面 Controller
 *
 * @author shun
 *
 */
@Controller
@RequiredArgsConstructor
public class LoginController {
//  /**ログインService*/
//  private final LoginService loginService;
//
//  /**パスワードエンコーダー*/
//  private final PasswordEncoder passwordEncoder;
//
//  /**メッセージソース*/
//  private final MessageSource messageSource;

  /**セッション情報*/
  private final HttpSession session;

  /**
   * 初期表示
   *
   * @param modelAndView モデル
   * @return 表示画面
   */
  @GetMapping(UrlConst.LOGIN)
  public ModelAndView view(ModelAndView modelAndView){
    // 空のフォームを作成
    modelAndView.addObject("loginForm", new LoginForm());
    // 遷移先の指定
    modelAndView.setViewName("login");
    return modelAndView;
  }

  /**
   * ログインエラー画面表示
   *
   * @param modelAndView
   * @return
   */
  @GetMapping(value=UrlConst.LOGIN,params="error")
  public ModelAndView viewError(ModelAndView modelAndView){
    var errorInfo = (Exception)session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    modelAndView.addObject("errorMsg",errorInfo.getMessage());
    // 空のフォームを作成
    modelAndView.addObject("loginForm", new LoginForm());
    // 遷移先の指定
    modelAndView.setViewName(UrlConst.LOGIN);
    return modelAndView;
  }
/**
 * ログイン処理はSpring Securityで行う
 * 設定はconfig/SecurityConfigで定義
 */
//  public ModelAndView login(){}
}
