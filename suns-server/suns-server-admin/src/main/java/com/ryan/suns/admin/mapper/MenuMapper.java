package com.ryan.suns.admin.mapper;

import com.ryan.suns.common.model.admin.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lr
 * @date 2018/3/26
 */
public interface MenuMapper extends SuperMapper<SysMenu>{
    
    List<SysMenu> findMenuByRoleCode(@Param("roleCode") String roleCode);
}
