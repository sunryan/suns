package com.ryan.sso.auth.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lr
 * @date 2018/2/1
 */
@Controller
public class LoginControl {
    
    @RequestMapping(value="/login")
    public String login(){
        return "login";
    }
    
    @RequestMapping(value="/index")
    
    public String index(){
        return "index";
    }
    
    @RequestMapping(value="/oauth/confirm_access")
    public String confirm_access(){
        return "confirm_access";
    }

}
