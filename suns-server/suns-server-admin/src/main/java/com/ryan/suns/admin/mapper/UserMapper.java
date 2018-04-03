package com.ryan.suns.admin.mapper;


import com.baomidou.mybatisplus.plugins.Page;
import com.ryan.suns.common.model.admin.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends SuperMapper<SysUser>{

    SysUser selectByUsername(@Param("username") String username);
    
    SysUser selectByUserId(@Param("userId") String userId);
    
    SysUser findUserByMobile(@Param("mobile") String mobile);
    
    List<SysUser> queryPage(@Param("page") Page<SysUser> page, @Param("sysUser") SysUser sysUser);
}