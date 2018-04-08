package com.ryan.suns.admin.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.ryan.suns.common.model.admin.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lr
 * @date 2018/4/3
 */
public interface RoleMapper extends SuperMapper<SysRole>{
    
    /**
     * 删除用户角色关联
     * @param userId
     * @return
     */
    int delUserRoles(@Param("userId") String userId);
    
    /**
     * 添加用户与角色关联
     * @param userId
     * @param roleIds
     * @return
     */
    int saveUserRoles(@Param("userId") String userId, @Param("roleIds") String[] roleIds);
    
    List<SysRole> queryPage(@Param("page") Page<SysRole> page, @Param("sysRole") SysRole sysRole);
    
    /**
     * 删除角色所有权限
     * @param roleId
     * @return
     */
    int deleteRoleMenus(@Param("roleId") String roleId);
    
    /**
     * 添加角色与权限关联
     * @param roleId
     * @param menuIds
     * @return
     */
    Integer saveRoleMenus(@Param("roleId") String roleId, @Param("menuIds") String[] menuIds);
    
    /**
     * 查询拥有roleId 角色的用户数量
     * @param roleId
     * @return
     */
    int findUserCountByroleId(@Param("roleId") String roleId);
}
