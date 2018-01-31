package com.ryan.suns.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by ryan on 2018/1/31.
 */

@SpringBootApplication
@MapperScan(basePackages = "com.ryan.suns.user.mapper")
public class UserAppliction {

    public static void main(String[] args) {
        SpringApplication.run(UserAppliction.class, args);
    }
}
