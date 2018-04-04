package com.ryan.suns.admin.mapper;

import com.ryan.suns.common.model.admin.SysRole;
import org.apache.ibatis.annotations.Param;

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
}
