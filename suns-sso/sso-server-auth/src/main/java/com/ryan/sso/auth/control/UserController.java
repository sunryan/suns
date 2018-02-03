package com.ryan.sso.auth.control;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 *
 * @author lr
 * @date 2018/2/3.
 */
@RestController
public class UserController {


    /**
     * 获取用户信息
     * @param user
     * @return
     */
    @GetMapping("/user")
    public Principal user(Principal user){
        return user;
    }
}
