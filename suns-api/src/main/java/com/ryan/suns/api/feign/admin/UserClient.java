package com.ryan.suns.api.feign.admin;

import com.ryan.suns.api.feign.config.FeignConfiguration;
import com.ryan.suns.common.model.admin.SysUser;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author lr
 * @date 2018/2/1
 */
@FeignClient(value = "suns-server-admin", path = "/user", configuration = FeignConfiguration.class)
public interface UserClient {
    
    
    /**
     * 根据username 获取用户信息和用户角色list
     * @param username
     * @return
     */
    @GetMapping("/name/{username}")
    SysUser findUserByUsername(@RequestParam(value = "username") String username);
    
    /**
     * 根据 userId 获取用户信息和用户角色list
     * @param userId
     * @return
     */
    @GetMapping("/{userId}")
    SysUser findUserByUserId(@RequestParam(value = "userId") String userId);
    
    /**
     * 根据 mobile 获取用户信息和用户角色list
     * @param mobile
     * @return
     */
    @GetMapping("/mobile/{mobile}")
    SysUser findUserByMobile(@RequestParam(value = "mobile") String mobile);
    
}
