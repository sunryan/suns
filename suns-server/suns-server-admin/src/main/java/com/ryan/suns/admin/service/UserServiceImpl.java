package com.ryan.suns.admin.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ryan.suns.api.user.UserService;
import com.ryan.suns.common.model.admin.SysUser;
import com.ryan.suns.admin.mapper.UserMapper;
import org.springframework.stereotype.Service;


/**
 * @author lr
 * @date 2018/1/23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, SysUser> implements UserService {

    @Override
    public SysUser selectByUsername(String username) {
        return baseMapper.selectByUsername(username);
    }
    
    @Override
    public SysUser selectByUserId(String userId) {
        return  baseMapper.selectByUserId(userId);
    }
    
    @Override
    public SysUser findUserByMobile(String mobile) {
        return baseMapper.findUserByMobile(mobile);
    }
    
    @Override
    public Page<SysUser> queryPage(Page<SysUser> page, SysUser sysUser) {
        
        return page.setRecords(baseMapper.queryPage(page, sysUser));
    }
}
