package com.ryan.suns.api.feign.admin;

import com.ryan.suns.api.feign.config.FeignConfiguration;
import com.ryan.suns.common.model.admin.SysMenu;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author lr
 * @date 2018/3/26
 */
@FeignClient(value = "suns-server-admin", path = "/menu", configuration = FeignConfiguration.class)
public interface MenuClient {
    
    
    @GetMapping("/roleCode/{roleCode}")
    List<SysMenu> findMenuByRoleCode(@RequestParam(value = "roleCode") String roleCode);
}