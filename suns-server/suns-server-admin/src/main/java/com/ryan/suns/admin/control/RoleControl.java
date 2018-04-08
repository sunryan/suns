package com.ryan.suns.admin.control;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ryan.suns.api.feign.admin.RoleClient;
import com.ryan.suns.api.user.RoleService;
import com.ryan.suns.common.BaseControl;
import com.ryan.suns.common.model.admin.SysRole;
import com.ryan.suns.common.model.enums.DeleteEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lr
 * @date 2018/4/3
 */
@RestController
@RequestMapping("/role")
public class RoleControl extends BaseControl implements RoleClient {

    @Autowired
    private RoleService roleService;
    
    @GetMapping("findAll")
    public ResponseEntity findAll(EntityWrapper<SysRole> wrapper){
        wrapper.eq("delFlag", "0");
        return ok(roleService.selectList(wrapper));
    }
    
    /**
     * 角色管理分页查询
     */
    @RequestMapping("/queryPage")
    public Page<SysRole> queryPage(Page<SysRole> page, SysRole sysRole) {
        return roleService.queryPage(page, sysRole);
    }
    
    
    /**
     * 添加角色
     * @param sysRole
     * @return
     */
    @PostMapping()
    public ResponseEntity insertUser(SysRole sysRole){
        if(roleService.insert(sysRole)){
            return ok();
        }else{
            return fail();
        }
    }
    
    /**
     * 修改用户
     * @param sysRole
     * @return
     */
    @PutMapping()
    public ResponseEntity updateUser(SysRole sysRole){
        if(sysRole.getId() == null){
            return fail("数据不正确");
        }
        if(roleService.updateById(sysRole)){
            return ok();
        }else{
            return fail();
        }
    }
    
    
    /**
     * 删除用户
     * @param roleId
     * @return
     */
    @DeleteMapping("/{roleId}")
    public ResponseEntity deleteUser(@PathVariable("roleId") String roleId){
        if(roleService.findUserCountByroleId(roleId) > 0){
            return fail("该角色已绑定用户，删除失败");
        }
        SysRole sysRole = new SysRole();
        sysRole.setId(roleId);
        sysRole.setDelFlag(DeleteEnum.DELETE);
        if(roleService.updateById(sysRole)){
            return ok();
        }else{
            return fail();
        }
    }
    
    
    /**
     * 角色绑定菜单
     * @param roleId
     * @param menuIds
     * @return
     */
    public ResponseEntity bindRoleMenus(@RequestParam(value = "roleId") String roleId, @RequestParam(value = "menuIds[]")  String[] menuIds){
        if(roleService.bindRoleMenus(roleId, menuIds)){
            return ok();
        }else{
            return fail();
        }
    }
}
