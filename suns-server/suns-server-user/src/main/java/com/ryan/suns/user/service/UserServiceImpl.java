package com.ryan.suns.user.service;

import com.ryan.suns.api.user.UserService;
import com.ryan.suns.common.model.user.SysUser;
import com.ryan.suns.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author lr
 * @date 2018/1/23
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public SysUser selectByUsername(String username) {
        return userMapper.selectByUsername(username);
    }
    
    @Override
    public SysUser selectByUserId(String userId) {
        return  userMapper.selectByUserId(userId);
    }
    
    @Override
    public SysUser findUserByMobile(String mobile) {
        return userMapper.findUserByMobile(mobile);
    }
}
