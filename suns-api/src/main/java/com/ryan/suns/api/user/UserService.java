package com.ryan.suns.api.user;


import com.ryan.suns.common.model.user.SysUser;

/**
 * @author lr
 * @date 2018/1/23
 */
public interface UserService {

    SysUser selectByUsername(String username);
    
    SysUser selectByUserId(String userId);
    
    SysUser findUserByMobile(String mobile);
}
