package com.ryan.suns.system.control;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lr
 * @date 2018/2/1
 */
@RestController
@RequestMapping("/")
public class TestControl {
    
    @GetMapping("/home")
    String home(){
        return "home";
    }
    
    @GetMapping("/cas")
    String cas(){
        return "cas";
    }
}
