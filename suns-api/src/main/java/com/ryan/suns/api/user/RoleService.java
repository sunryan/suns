package com.ryan.suns.api.user;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.ryan.suns.common.model.admin.SysRole;

/**
 * @author lr
 * @date 2018/1/23
 */
public interface RoleService  extends IService<SysRole> {
    
    boolean updateUserRoles(String userId, String[] roleIds);
    
    /**
     * 角色管理分页查询
     * @param page
     * @param sysRole
     * @return
     */
    Page<SysRole> queryPage(Page<SysRole> page, SysRole sysRole);
    
    /**
     * 角色绑定菜单
     * @param roleId
     * @param menuIds
     * @return
     */
    boolean bindRoleMenus(String roleId, String[] menuIds);
    
    /**
     * 查询拥有roleId 角色的用户数量
     * @param roleId
     * @return
     */
    int findUserCountByroleId(String roleId);
}
