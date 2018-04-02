package com.ryan.suns.admin.mapper;


import com.baomidou.mybatisplus.plugins.Page;
import com.ryan.suns.common.model.admin.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper extends SuperMapper<SysUser>{

    SysUser selectByUsername(@Param("username") String username);
    
    SysUser selectByUserId(@Param("userId") String userId);
    
    SysUser findUserByMobile(@Param("mobile") String mobile);
    
    List<SysUser> queryPage(@Param("page") Page<SysUser> page, @Param("sysUser") SysUser sysUser);
}