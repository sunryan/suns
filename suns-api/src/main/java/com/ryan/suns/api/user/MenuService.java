package com.ryan.suns.api.user;

import com.baomidou.mybatisplus.service.IService;
import com.ryan.suns.common.model.admin.SysMenu;

import java.util.List;

/**
 * @author lr
 * @date 2018/3/26
 */
public interface MenuService extends IService<SysMenu> {
    
    List<SysMenu> findMenuByRoleCode(String roleCode);
    
    /**
     * 查找菜单 tree结构
     * @return
     */
    List<SysMenu> findMenuTree();
}
