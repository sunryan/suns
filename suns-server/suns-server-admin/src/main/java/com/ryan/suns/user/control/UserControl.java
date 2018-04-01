package com.ryan.suns.user.control;

import com.baomidou.mybatisplus.plugins.Page;
import com.ryan.suns.api.feign.admin.UserClient;
import com.ryan.suns.api.user.UserService;
import com.ryan.suns.common.model.admin.SysUser;
import com.ryan.suns.common.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lr
 * @date 2018/2/1
 */
@RestController
@RequestMapping("/user")
public class UserControl implements UserClient {
    
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
    
    
    /**
     * 用户管理分页查询
     */
    @RequestMapping("/queryPage")
    public Page<SysUser> queryPage(Page<SysUser> page, SysUser sysUser) {
        return userService.queryPage(page, sysUser);
    }
    
    
    @PostMapping("/insertOrUpdateUser")
    public ResponseEntity insertOrUpdateUser(SysUser sysUser){
        if(userService.insertOrUpdate(sysUser)){
            return new ResponseEntity<R<String>>(new R(), HttpStatus.OK);
        }else{
            return new ResponseEntity<R<String>>(new R().fail("请登陆"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
