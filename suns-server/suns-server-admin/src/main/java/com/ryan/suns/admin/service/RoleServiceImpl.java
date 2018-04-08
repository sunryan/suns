package com.ryan.suns.admin.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ryan.suns.admin.mapper.RoleMapper;
import com.ryan.suns.api.user.RoleService;
import com.ryan.suns.common.model.admin.SysRole;
import org.springframework.stereotype.Service;

/**
 * @author lr
 * @date 2018/4/3
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, SysRole> implements RoleService {
    
    @Override
    public boolean updateUserRoles(String userId, String[] roleIds) {
        baseMapper.delUserRoles(userId);
        return retBool(baseMapper.saveUserRoles(userId, roleIds));
    }
    
    @Override
    public Page<SysRole> queryPage(Page<SysRole> page, SysRole sysRole) {
        return page.setRecords(baseMapper.queryPage(page, sysRole));
    }
    
    @Override
    public boolean bindRoleMenus(String roleId, String[] menuIds) {
        baseMapper.deleteRoleMenus(roleId);
        return retBool(baseMapper.saveRoleMenus(roleId, menuIds));
    }
    
    @Override
    public int findUserCountByroleId(String roleId) {
        return baseMapper.findUserCountByroleId(roleId);
    }
}
