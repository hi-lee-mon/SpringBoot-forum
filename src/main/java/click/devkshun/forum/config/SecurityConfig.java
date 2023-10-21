package click.devkshun.forum.config;

import click.devkshun.forum.constant.UrlConst;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
  /**ユーザ名のname属性*/
  private final String OVERRIDE_USERNAME_PARAMETER = "loginId";
  /**削除対象のセッションID*/
  private final String JSESSIONID = "JSESSIONID";
  /**パスワードエンコーダー*/
  private final PasswordEncoder passwordEncoder;
  /**ユーザ情報取得*/
  private final UserDetailsService userDetailsService;
  /**メッセージ情報*/
  private final MessageSource messageSource;

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(authz -> authz //URL毎の認可設定記述開始
        .requestMatchers(UrlConst.NO_AUTHENTICATION).permitAll()
        .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
          .permitAll()// /css/**などログインしていなくてもアクセス可能とする
        .anyRequest().authenticated() // 上記以外の全てのURLはログイン後のみアクセス可能
    ).formLogin(login -> login //  フォーム認証の設定記載開始(フォームを利用した認証をすると宣言する)
        .loginPage(UrlConst.LOGIN) // ログイン画面のURL指定
            .permitAll()// ログイン画面は誰でも参照可能
        .loginProcessingUrl(UrlConst.LOGIN) // ユーザ名・パスワードの送信先URL
        .usernameParameter(OVERRIDE_USERNAME_PARAMETER)
        .defaultSuccessUrl(UrlConst.HOME) // ログイン成功後のリダイレクト先
        .failureUrl(UrlConst.LOGIN + "?error") // ログイン失敗後のリダイレクト先
    ).logout(logout -> logout // ログアウト設定記述開始
        .logoutSuccessUrl(UrlConst.LOGIN) // ログアウト後のリダイレクト先
        .invalidateHttpSession(true) // HTTPセッションの無効化。trueでセッションの削除を行う(ログアウトするなら削除するのが当たり前なのでおまじない)
        .deleteCookies(JSESSIONID) // 対象のクッキーを削除
    );

    return http.build();
  }

  /**
   * Provider定義
   * @return カスタマイズProvider
   */
  @Bean
  public AuthenticationProvider daoAuthenticationProvider(){
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    // providerの設定
    provider.setUserDetailsService(userDetailsService); // 必須　同じ項目を詰めている
    provider.setPasswordEncoder(passwordEncoder); // 必須　同じ項目を詰めている
    provider.setMessageSource(messageSource); // ここがカスタマイズされている。

    // カスタムしたproviderを返す
    return provider;
  }
}
