package com.ryan.suns.admin.control;

import com.baomidou.mybatisplus.plugins.Page;
import com.ryan.suns.api.feign.admin.UserClient;
import com.ryan.suns.api.user.UserService;
import com.ryan.suns.common.BaseControl;
import com.ryan.suns.common.model.admin.SysUser;
import com.ryan.suns.common.model.enums.DeleteEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lr
 * @date 2018/2/1
 */
@RestController
@RequestMapping("/user")
public class UserControl extends BaseControl implements UserClient {
    
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    
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
    
    
    /**
     * 添加用户
     * @param sysUser
     * @return
     */
    @PostMapping()
    public ResponseEntity insertUser(SysUser sysUser,
                                             @RequestParam(value = "roleIds[]")  String[] roleIds){
        //默认用户名
        sysUser.setPassword(passwordEncoder.encode(sysUser.getUsername()));
        if(userService.insert(sysUser)){
            return ok();
        }else{
            return fail();
        }
    }
    
    /**
     * 修改用户
     * @param sysUser
     * @return
     */
    @PutMapping()
    public ResponseEntity updateUser(SysUser sysUser,
                                             @RequestParam(value = "roleIds[]")  String[] roleIds){
        if(userService.updateById(sysUser)){
            return ok();
        }else{
            return fail();
        }
    }
    
    
    /**
     * 删除用户
     * @param sysUser
     * @return
     */
    @DeleteMapping("/deleteUser")
    public ResponseEntity deleteUser(SysUser sysUser){
        if(sysUser.getUserId() == null){
            return fail("数据不正确");
        }
        sysUser.setDelFlag(DeleteEnum.DELETE);
        if(userService.updateById(sysUser)){
            return ok();
        }else{
            return fail();
        }
    }
    
}
