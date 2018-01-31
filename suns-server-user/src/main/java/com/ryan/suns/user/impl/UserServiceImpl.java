package com.ryan.suns.user.impl;

import com.ryan.suns.api.auth.UserService;
import com.ryan.suns.common.model.auth.User;
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
    public User selectByUsername(String username) {
        return userMapper.selectByUsername(username);
    }
}
