package com.ryan.suns.user.control;

import com.ryan.suns.api.auth.UserService;
import com.ryan.suns.api.feign.user.UserClient;
import com.ryan.suns.common.model.auth.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lr
 * @date 2018/2/1
 */
@RestController
@RequestMapping("/api/user")
public class UserControl  implements UserClient {
    
    @Autowired
    private UserService userService;
    
    @Override
    public User selectByUsername(String username) {
        return userService.selectByUsername(username);
    }

    @GetMapping("/ceshi")
    public String ceshi(){
        return "ceshi";
    }
}
