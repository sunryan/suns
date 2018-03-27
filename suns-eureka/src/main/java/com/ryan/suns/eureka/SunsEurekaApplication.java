package com.ryan.suns.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by ryan on 2018/1/31.
 */

@EnableEurekaServer
@SpringBootApplication
public class SunsEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SunsEurekaApplication.class, args);
    }
}
