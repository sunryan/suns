package com.ryan.suns.auth.component.mobile;

import com.ryan.suns.api.feign.admin.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @author lr
 * @date 2018/1/9
 * 手机号登录配置入口
 */
@Component
public class MobileSecurityConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Autowired
    private AuthenticationSuccessHandler mobileLoginSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler mobileLoginFailureHandler;
    @Autowired
    private UserClient userClient;
    
    
    @Override
    public void configure(HttpSecurity http) {
        MobileAuthenticationFilter mobileAuthenticationFilter = new MobileAuthenticationFilter();
        mobileAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        mobileAuthenticationFilter.setAuthenticationSuccessHandler(mobileLoginSuccessHandler);
        mobileAuthenticationFilter.setAuthenticationFailureHandler(mobileLoginFailureHandler);

        MobileAuthenticationProvider mobileAuthenticationProvider = new MobileAuthenticationProvider();
        mobileAuthenticationProvider.setUserClient(userClient);
        http.authenticationProvider(mobileAuthenticationProvider)
                .addFilterAfter(mobileAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
