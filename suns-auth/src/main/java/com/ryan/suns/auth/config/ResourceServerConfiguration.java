package com.ryan.suns.auth.config;


import com.ryan.suns.auth.component.FormAuthenticationConfig;
import com.ryan.suns.auth.component.mobile.MobileSecurityConfigurer;
import com.ryan.suns.auth.component.social.SunsSocialConfigurer;
import com.ryan.suns.common.config.FilterUrlsPropertiesConifg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author lr
 *
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    
    @Autowired
    private FilterUrlsPropertiesConifg filterUrlsPropertiesConifg;
    @Autowired
    private MobileSecurityConfigurer mobileSecurityConfigurer;
    @Autowired
    private SunsSocialConfigurer sunsSocialConfigurer;
    @Autowired
    private FormAuthenticationConfig formAuthenticationConfig;
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http
                .authorizeRequests();
        for (String url : filterUrlsPropertiesConifg.getAnon()) {
            registry.antMatchers(url).permitAll();
        }
        registry.anyRequest().authenticated()
                .and()
                .csrf().disable();
    
        formAuthenticationConfig.configure(http);
        http
                .apply(mobileSecurityConfigurer)
                .and()
                .apply(sunsSocialConfigurer);
    }
    
}