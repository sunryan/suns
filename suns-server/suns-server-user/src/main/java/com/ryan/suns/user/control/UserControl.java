package com.ryan.suns.user.control;

import com.ryan.suns.api.user.UserService;
import com.ryan.suns.api.feign.user.UserClient;
import com.ryan.suns.common.model.user.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
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
    public SysUser findUserByUsername(String username) {
        return userService.selectByUsername(username);
    }
    
    @Override
    public SysUser findUserByUserId(String userId) {
        return userService.selectByUserId(userId);
    }
    
    @Override
    public SysUser findUserByMobile(String mobile) {
        return userService.findUserByMobile(mobile);
    }
    
}
