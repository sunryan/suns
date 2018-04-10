package com.ryan.suns.admin.service;

import com.baomidou.mybatisplus.plugins.Page;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ryan.suns.admin.mapper.RoleMapper;
import com.ryan.suns.api.user.RoleService;
import com.ryan.suns.common.model.admin.SysMenu;
import com.ryan.suns.common.model.admin.SysRole;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author lr
 * @date 2018/4/3
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, SysRole> implements RoleService {
    
    @Override
    public boolean updateUserRoles(String userId, String[] roleIds) {
        if(roleIds.length == 0){
            return false;
        }
        List<String> roleIdList = baseMapper.selectUserRoles(userId);
        if(roleIdList.size() == roleIds.length){
            Collection<String> distinct = CollUtil.disjunction(roleIdList, Arrays.asList(roleIds));
            if(distinct.size() == 0 ){
                return true;
            }
        }
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
    
    @Override
    public List<SysMenu> findRoleMenus(String roleId) {
        return baseMapper.findRoleMenus(roleId);
    }
}
