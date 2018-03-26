package com.ryan.suns.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lr
 *         获取用户信息也是通过这个应用实现
 *         这里既是认证服务器，也是资源服务器
 *         EnableResourceServer
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.ryan.suns.api.feign")
@ComponentScan(basePackages = {"com.ryan.suns.auth", "com.ryan.suns.common.config"})
public class SunsAuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SunsAuthServerApplication.class, args);
    }

}
