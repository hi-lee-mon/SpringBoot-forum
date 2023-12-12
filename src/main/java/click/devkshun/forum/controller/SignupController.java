package click.devkshun.forum.controller;

import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import click.devkshun.forum.constant.SignupMessageEnum;
import click.devkshun.forum.constant.UrlConst;
import click.devkshun.forum.constant.ViewNameConst;
import click.devkshun.forum.entity.UserInfo;
import click.devkshun.forum.form.SignupForm;
import click.devkshun.forum.service.SignupService;
import click.devkshun.forum.util.AppUtil;
import lombok.RequiredArgsConstructor;

/**
 * ユーザ登録画面 Controller
 *
 * @author shun
 *
 */
@Controller
@RequiredArgsConstructor
public class SignupController {
  /**ログインService*/
  private final SignupService signupService;

  /**メッセージソース*/
  private final MessageSource messageSource;

  /**
   * 初期表示
   *
   * @param modelAndView モデル
   * @return 表示画面
   */
  @GetMapping(UrlConst.SIGNUP)
  public ModelAndView view(ModelAndView modelAndView){
    // 空のフォームを作成
    modelAndView.addObject("signupForm", new SignupForm());
    // 遷移先の指定
    modelAndView.setViewName(ViewNameConst.SIGNUP);
    return modelAndView;
  }

  /**
   * ユーザ登録処理
   *
   * @param modelAndView モデル
   * @param signupForm 入力情報
   * @return 表示画面
   */
  @PostMapping(UrlConst.SIGNUP)
  public ModelAndView signup(
      ModelAndView modelAndView,
      @Validated SignupForm signupForm,
      BindingResult bindingResult
  ){
    if(bindingResult.hasErrors()){
      // バリデーションエラー処理
      modelAndView.setViewName(ViewNameConst.SIGNUP);
      return modelAndView;
    }

    // DBからユーザ取得
    Optional<UserInfo> userInfoOpt = signupService.registerUserInfo(signupForm);
    // ユーザの有無によってメッセージEnumを取得
    SignupMessageEnum messageEnum = judgeMessageEnum(userInfoOpt);
    String message = AppUtil.getMessage(messageSource, messageEnum.getMessageId());
    modelAndView.addObject("message",message);
    modelAndView.addObject("isError",messageEnum.isError());
    modelAndView.setViewName(ViewNameConst.SIGNUP);
    return modelAndView;
  }

  /**
   * ユーザ情報登録の結果によってメッセージキーを判断する
   *
   * @param userInfoOpt ユーザ登録結果(登録済だった場合はEmpty)
   * @return メッセージキーとメッセージ種別のEnum
   */
  private SignupMessageEnum judgeMessageEnum(Optional<UserInfo> userInfoOpt){
    if(userInfoOpt.isEmpty()){
      return SignupMessageEnum.EXISTS_LOGIN_ID;
    }else{
      return SignupMessageEnum.SUCCESS;
    }
  }
}
