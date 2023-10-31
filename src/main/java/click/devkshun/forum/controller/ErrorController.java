package click.devkshun.forum.controller;

import click.devkshun.forum.constant.UrlConst;
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
public class ErrorController {
  /**
   * 初期表示
   *
   * @return 表示画面
   */
  @GetMapping(value=UrlConst.Error,params="continue")
  public ModelAndView view(ModelAndView modelAndView){
    // 遷移先の指定
    modelAndView.setViewName(UrlConst.Error);
    return modelAndView;
  }

}
