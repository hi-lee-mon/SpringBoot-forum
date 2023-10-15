package click.devkshun.forum.controller;

import click.devkshun.forum.constant.UrlConst;
import click.devkshun.forum.form.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

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
  public ModelAndView view(ModelAndView modelAndView){
    // 遷移先の指定
    modelAndView.setViewName(UrlConst.HOME);
    return modelAndView;
  }

}
