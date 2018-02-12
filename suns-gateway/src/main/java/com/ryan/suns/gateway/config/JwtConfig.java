package com.ryan.suns.gateway.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author lr
 * @date 2018/2/12
 */
@Configuration
@Getter
public class JwtConfig {
    
    @Value("${jwt.pri-key.path}")
    private String priKeyPath;
    @Value("${jwt.pub-key.path}")
    private String pubKeyPath;
    
    
}
