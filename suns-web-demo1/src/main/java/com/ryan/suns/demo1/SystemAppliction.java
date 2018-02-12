package com.ryan.suns.demo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lr
 * @date 2018/2/9
 */
@SpringBootApplication
@RestController
public class SystemAppliction {
    
    public static void main(String[] args) {
        SpringApplication.run(SystemAppliction.class, args);
    }
    
    @RequestMapping("/")
    public String welcome() {
        return "index";
    }
    
    @RequestMapping("/index")
    public String index() {
        return "index";
    }
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @RequestMapping("/user")
    public String user() {
        return "user";
    }
    
    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }
    
    @RequestMapping("/world")
    public String world() {
        return "world";
    }
}
