package com.ryan.suns.user.impl;

import com.ryan.suns.api.auth.RoleService;
import com.ryan.suns.user.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lr
 * @date 2018/1/23
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;


}
