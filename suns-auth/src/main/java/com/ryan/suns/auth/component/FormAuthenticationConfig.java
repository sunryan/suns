package com.ryan.suns.auth.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * @author lr
 * @date 2018/3/26
 */
@Component
public class FormAuthenticationConfig {
    
    @Autowired
    protected AuthenticationSuccessHandler mobileLoginSuccessHandler;
    
    @Autowired
    protected AuthenticationFailureHandler authenctiationFailureHandler;
    
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                //没有登录时跳转接口
                .loginPage("/form/require")
                //登录接口
                .loginProcessingUrl("/form/login")
                .successHandler(mobileLoginSuccessHandler)
                .failureHandler(authenctiationFailureHandler);
    }
}
