package com.ryan.suns.gateway.config;

import com.ryan.suns.gateway.filter.TokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lr
 * @date 2018/2/12
 */
@Configuration
public class ZuulConfig {

    @Bean
    public TokenFilter TokenFilter(){
        return new TokenFilter();
    }
}
