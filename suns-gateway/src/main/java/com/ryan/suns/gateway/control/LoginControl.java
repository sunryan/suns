package com.ryan.suns.gateway.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author lr
 * @date 2018/2/1
 */
@Controller
public class LoginControl {
    
    
    @RequestMapping(value="/login",method= RequestMethod.GET)
    public String login(){
        return "login";
    }
    
    
    @RequestMapping(value="/index")
    public String index(){
        return "index";
    }

    @RequestMapping(value="/user")
    public String user(){
        return "user";
    }

}
