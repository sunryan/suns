package com.ryan.suns.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by ryan on 2018/1/31.
 */

@EnableTransactionManagement
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.ryan.suns.admin.mapper")
public class SunsAdminAppliction {

    public static void main(String[] args) {
        SpringApplication.run(SunsAdminAppliction.class, args);
    }
}
