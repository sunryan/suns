package com.ryan.suns.gateway.config;

import com.ryan.suns.gateway.filter.JWTAuthenticationFilter;
import com.ryan.suns.gateway.filter.JWTLoginFilter;
import com.ryan.suns.gateway.service.UserPasswordProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * Security配置
 * @author lr
 * @date 2018/1/17
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // 启用方法安全设置
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    private static final String SITE_WIDE_SECRET = "suns";
    
    
    @Bean
    public UserPasswordProvider userPasswordProvider(){
        return new UserPasswordProvider();
    }
    
    /**
     * 密码校验
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder(SITE_WIDE_SECRET);
    }
    
    /**
     * JWT登录过滤器
     * @param authManager
     * @return
     */
    @Bean
    public JWTLoginFilter jwtLoginFilter( AuthenticationManager authManager){
        return new JWTLoginFilter("/login", authManager);
    }
    
    /**
     * JWT校验过滤器
     * @return
     */
    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter(){
        return  new JWTAuthenticationFilter();
    }
    
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭csrf验证
        http.csrf().disable()
                // 对请求进行认证
                .authorizeRequests()
                // 允许对于网站静态资源的无授权访问
                .antMatchers(
                        "/login",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/image/**"
                ).permitAll()
                // 所有 /login 的POST请求 都放行
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                // 权限检查
                .antMatchers("/user").hasAuthority("user")
                // 角色检查
                .antMatchers("/test").hasRole("admin")
                // 所有请求需要身份认证
                .anyRequest().authenticated()
                .and()
                    //指定登录页
                    .formLogin().loginPage("/login")
                    .permitAll()
                .and()
                    //退出成功后默认跳转到
                    .logout()
                    .logoutSuccessUrl("/login")
                    .invalidateHttpSession(true)
                    .permitAll()
                .and()
                // 添加一个过滤器 所有访问 /login 的POST请求交给 JWTLoginFilter 来处理 这个类处理所有的JWT相关内容
                .addFilterBefore(jwtLoginFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                // 添加一个过滤器验证其他请求的Token是否合法
                .addFilterAfter(jwtAuthenticationFilter(), JWTLoginFilter.class);
        
        // 禁用缓存
        http.headers().cacheControl();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 使用自定义身份验证组件
        auth.authenticationProvider(userPasswordProvider());
    }

}
