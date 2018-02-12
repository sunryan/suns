package com.ryan.suns.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lr
 * @date 2018/2/1
 */
@SpringBootApplication
public class SystemAppliction {
    
    public static void main(String[] args) {
        SpringApplication.run(SystemAppliction.class, args);
    }
}
