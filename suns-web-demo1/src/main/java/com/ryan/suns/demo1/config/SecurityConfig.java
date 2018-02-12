package com.ryan.suns.demo1.config;

import com.ryan.suns.demo1.filter.JWTAuthenticationFilter;
import com.ryan.suns.demo1.filter.JWTLoginFilter;
import com.ryan.suns.demo1.provider.CustomAuthenticationProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security 配置类.
 * 
 * @since 1.0.0 2017年3月23日
 * @author <a href="https://waylau.com">Way Lau</a>
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭csrf验证
        http.csrf().disable()
                // 对请求进行认证
                .authorizeRequests()
                // 允许对于网站静态资源的无授权访问
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                ).permitAll()
                // 所有 /login 的POST请求 都放行
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                // 权限检查
                .antMatchers("/hello").hasAuthority("HELLO")
                // 角色检查
                .antMatchers("/world").hasRole("WORLD")
                // 所有请求需要身份认证
                .anyRequest().authenticated()
                .and()
                    //指定登录页
                    .formLogin().loginPage("/login")
                    //登录成功后默认跳转到
                    .defaultSuccessUrl("/user")
                    .permitAll()
                .and()
                    //退出成功后默认跳转到
                    .logout()
                    .logoutSuccessUrl("/login")
                    .invalidateHttpSession(true)
                    .permitAll()
                .and()
                // 添加一个过滤器 所有访问 /login 的POST请求交给 JWTLoginFilter 来处理 这个类处理所有的JWT相关内容
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                // 添加一个过滤器验证其他请求的Token是否合法
                .addFilterAfter(new JWTAuthenticationFilter(),
                        JWTLoginFilter.class);
    
        // 禁用缓存
        http.headers().cacheControl();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 使用自定义身份验证组件
        auth.authenticationProvider(new CustomAuthenticationProvider());
        
    }

}
