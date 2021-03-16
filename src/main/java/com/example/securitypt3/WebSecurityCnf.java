package com.example.securitypt3;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * SpringSecurityを利用するための設定クラス
 * ログイン処理でのパラメータ、画面遷移や認証処理でのデータアクセス先を設定する
 */
@Configuration
@EnableWebSecurity //1
public class WebSecurityCnf extends WebSecurityConfigurerAdapter { //2
    /**
     * 認可設定を無視するリクエストを設定
     * 静的リソース(image,javascript,css)を認可処理の対象から除外する
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
        		.antMatchers("/css/**", "/js/**", "/images/**"); //3
    }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
    	http.authorizeRequests()
    		.anyRequest().authenticated() //1
    		.and()
    		.formLogin()
    		.loginPage("/login")
    		.permitAll();
        }
}
