package com.ryan.suns.auth.component.social.qq.config;

import com.ryan.suns.auth.config.SocialPropertiesConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author lr
 * @date 2018/1/19
 * QQ登录参数配置
 */
@Configuration
@ConfigurationProperties(prefix = "suns.social.qq")
public class SocialQQPropertiesConfig extends SocialPropertiesConfig {
}
