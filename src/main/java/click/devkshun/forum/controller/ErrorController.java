package click.devkshun.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import click.devkshun.forum.constant.UrlConst;
import click.devkshun.forum.constant.ViewNameConst;

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
   * @param modelAndView 表示用モデル
   * @return 表示画面
   */
  @GetMapping(value=UrlConst.ERROR,params="continue")
  public ModelAndView view(ModelAndView modelAndView){
    // 遷移先の指定
    modelAndView.setViewName(ViewNameConst.ERROR);
    return modelAndView;
  }

}
