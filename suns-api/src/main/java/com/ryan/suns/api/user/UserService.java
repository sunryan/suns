package com.ryan.suns.api.user;


import com.baomidou.mybatisplus.service.IService;
import com.ryan.suns.common.model.user.SysUser;

/**
 * @author lr
 * @date 2018/1/23
 */
public interface UserService  extends IService<SysUser> {

    SysUser selectByUsername(String username);
    
    SysUser selectByUserId(String userId);
    
    SysUser findUserByMobile(String mobile);
}
