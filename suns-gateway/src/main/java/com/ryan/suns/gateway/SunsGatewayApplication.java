package com.ryan.suns.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


/**
 * @author lr
 */
@EnableZuulProxy
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableFeignClients(basePackages = "com.ryan.suns.api.feign")
@ComponentScan(basePackages = {"com.ryan.suns.gateway", "com.ryan.suns.common.config"})
public class SunsGatewayApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(SunsGatewayApplication.class, args);
    }
    
    
    @Bean
    LoadBalancerInterceptor loadBalancerInterceptor(LoadBalancerClient loadBalance){
        return new LoadBalancerInterceptor(loadBalance);
    }
}
