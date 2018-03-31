package com.ryan.suns.api.user;

import com.ryan.suns.common.model.admin.SysMenu;

import java.util.List;

/**
 * @author lr
 * @date 2018/3/26
 */
public interface MenuService {
    
    List<SysMenu> findMenuByRoleCode(String roleCode);
}
