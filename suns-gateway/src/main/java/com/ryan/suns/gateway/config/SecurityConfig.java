package com.ryan.suns.gateway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * Security配置
 * @author lr
 * @date 2018/1/17
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                //允许所有用户访问
                .antMatchers("/sys/home", "/auth/oauth/token").permitAll()
                //其他地址的访问均需验证权限
                .anyRequest().authenticated()
                .and()
                    //指定登录页
                    .formLogin().loginPage("/sys/login")
                    //登录成功后默认跳转到
                    .defaultSuccessUrl("/index")
                    .failureUrl("/sys/login?error").permitAll()
                .and()
                    //退出成功后默认跳转到
                    .logout().logoutSuccessUrl("/sys/login").permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/sys/static/**","/sys/js/**","/js/**");
//        web.ignoring().antMatchers("/auth/oauth/token");
    }
}
