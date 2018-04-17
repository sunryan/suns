package com.ryan.suns.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;

/**
 * @author lr
 * @date 2018/4/16
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZipkinStreamServer //使用Stream方式启动ZipkinServer
public class SunsZipkinApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(SunsZipkinApplication.class, args);
    }
}
