package com.ryan.suns.user.mapper;


import com.ryan.suns.common.model.user.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper extends SuperMapper<SysUser>{

    SysUser selectByUsername(@Param("username") String username);
    
    SysUser selectByUserId(@Param("userId") String userId);
    
    SysUser findUserByMobile(@Param("mobile") String mobile);
}