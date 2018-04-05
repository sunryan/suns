package com.ryan.suns.api.feign.admin;

import com.ryan.suns.common.model.admin.SysMenu;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author lr
 * @date 2018/3/26
 */
@FeignClient(name = "suns-server-admin", path = "/menu")
public interface MenuClient {
    
    
    @RequestMapping(value = "/roleCode/{roleCode}", method = RequestMethod.GET)
    List<SysMenu> findMenuByRoleCode(@PathVariable("roleCode") String roleCode);
}
