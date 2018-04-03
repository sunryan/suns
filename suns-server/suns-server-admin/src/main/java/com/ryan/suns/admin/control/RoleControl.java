package com.ryan.suns.admin.control;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ryan.suns.api.feign.admin.RoleClient;
import com.ryan.suns.api.user.RoleService;
import com.ryan.suns.common.BaseControl;
import com.ryan.suns.common.model.admin.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
