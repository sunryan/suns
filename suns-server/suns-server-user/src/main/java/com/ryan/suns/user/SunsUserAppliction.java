package com.ryan.suns.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by ryan on 2018/1/31.
 */

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.ryan.suns.user.mapper")
public class SunsUserAppliction {

    public static void main(String[] args) {
        SpringApplication.run(SunsUserAppliction.class, args);
    }
}
