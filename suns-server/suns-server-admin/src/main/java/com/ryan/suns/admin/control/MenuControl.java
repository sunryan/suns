package com.ryan.suns.admin.control;

import com.ryan.suns.api.feign.admin.MenuClient;
import com.ryan.suns.api.user.MenuService;
import com.ryan.suns.common.model.admin.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lr
 * @date 2018/3/26
 */
@RestController
@RequestMapping("/menu")
public class MenuControl implements MenuClient {
    
    @Autowired
    private MenuService menuService;
    
    @Override
    public List<SysMenu> findMenuByRoleCode(String roleCode) {
        return menuService.findMenuByRoleCode(roleCode);
    }
}
