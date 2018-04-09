package com.ryan.suns.admin.control;

import com.ryan.suns.api.feign.admin.MenuClient;
import com.ryan.suns.api.user.MenuService;
import com.ryan.suns.common.BaseControl;
import com.ryan.suns.common.model.admin.SysMenu;
import com.ryan.suns.common.model.enums.DeleteEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lr
 * @date 2018/3/26
 */
@RestController
@RequestMapping("/menu")
public class MenuControl extends BaseControl implements MenuClient {
    
    @Autowired
    private MenuService menuService;
    
    @Override
    public List<SysMenu> findMenuByRoleCode(@PathVariable("roleCode") String roleCode) {
        return menuService.findMenuByRoleCode(roleCode);
    }
    
    @GetMapping("/tree")
    public ResponseEntity findMenuTree(){
        return ok(menuService.findMenuTree());
    }
    
    
    /**
     * 添加菜单
     * @param sysMenu
     * @return
     */
    @PostMapping()
    public ResponseEntity insertMenu(SysMenu sysMenu){
        if(menuService.insert(sysMenu)){
            return ok();
        }else{
            return fail();
        }
    }
    
    /**
     * 修改菜单
     * @param sysMenu
     * @return
     */
    @PutMapping()
    public ResponseEntity updateMenu(SysMenu sysMenu){
        if(sysMenu.getId() == null){
            return fail("数据不正确");
        }
        if(menuService.updateById(sysMenu)){
            return ok();
        }else{
            return fail();
        }
    }
    
    /**
     * 删除菜单
     * @param roleId
     * @return
     */
    @DeleteMapping("/{roleId}")
    public ResponseEntity deleteMenu(@PathVariable("roleId") String roleId){
        SysMenu sysMenu = new SysMenu();
        sysMenu.setId(roleId);
        sysMenu.setDelFlag(DeleteEnum.DELETE);
        if(menuService.updateById(sysMenu)){
            return ok();
        }else{
            return fail();
        }
    }
}
