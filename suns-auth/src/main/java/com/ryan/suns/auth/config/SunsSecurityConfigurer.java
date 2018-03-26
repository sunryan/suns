package com.ryan.suns.auth.config;

import com.ryan.suns.auth.component.FormAuthenticationConfig;
import com.ryan.suns.auth.component.mobile.MobileSecurityConfigurer;
import com.ryan.suns.auth.component.social.SunsSocialConfigurer;
import com.ryan.suns.common.config.FilterUrlsPropertiesConifg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * 如果继承WebSecurityConfigurerAdapter
 * AuthorizationServerSecurityConfiguration 这个配置会和自定义配置一起起作用
 *
 * @author lr
 * @date 2018/3/26
 */
@Configuration
@EnableWebSecurity
public class SunsSecurityConfigurer extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private FormAuthenticationConfig formAuthenticationConfig;
    @Autowired
    private FilterUrlsPropertiesConifg filterUrlsPropertiesConifg;
    @Autowired
    private MobileSecurityConfigurer mobileSecurityConfigurer;
    @Autowired
    private SunsSocialConfigurer sunsSocialConfigurer;
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
    
        formAuthenticationConfig.configure(http);
        
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry =
                http.authorizeRequests();
        for (String url : filterUrlsPropertiesConifg.getAnon()) {
            registry.antMatchers(url).permitAll();
        }
        
        registry
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
        http
                .apply(mobileSecurityConfigurer)
            .and()
                .apply(sunsSocialConfigurer);
    }
}
