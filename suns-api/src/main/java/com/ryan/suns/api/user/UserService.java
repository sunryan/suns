package com.ryan.suns.api.user;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.ryan.suns.common.model.admin.SysUser;

/**
 * @author lr
 * @date 2018/1/23
 */
public interface UserService  extends IService<SysUser> {

    SysUser selectByUsername(String username);
    
    SysUser selectByUserId(String userId);
    
    SysUser findUserByMobile(String mobile);
    
    /**
     * 用户管理分页查询
     * @param page
     * @param sysUser
     * @return
     */
    Page<SysUser> queryPage(Page<SysUser> page, SysUser sysUser);
}
