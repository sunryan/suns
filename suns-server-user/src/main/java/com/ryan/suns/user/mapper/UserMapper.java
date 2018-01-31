package com.ryan.suns.user.mapper;


import com.ryan.suns.common.model.auth.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {

    User selectByUsername(@Param("username") String username);
}