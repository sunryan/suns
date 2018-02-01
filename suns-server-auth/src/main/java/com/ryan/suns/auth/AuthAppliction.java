package com.ryan.suns.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @author lr
 * @date 2018/1/23
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.ryan.suns.api.feign")
public class AuthAppliction {
    
    public static void main(String[] args) {
        SpringApplication.run(AuthAppliction.class, args);
    }
}
