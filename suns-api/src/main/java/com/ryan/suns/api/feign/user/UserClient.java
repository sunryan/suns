package com.ryan.suns.api.feign.user;

import com.ryan.suns.api.feign.config.FeignConfiguration;
import com.ryan.suns.common.model.user.SysUser;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author lr
 * @date 2018/2/1
 */
@FeignClient(value = "suns-server-user", path = "/api/user", configuration = FeignConfiguration.class)
public interface UserClient {
    
    
    @GetMapping("/name/{username}")
    SysUser findUserByUsername(@RequestParam(value = "username") String username);
    
    @GetMapping("/{userId}")
    SysUser findUserByUserId(@RequestParam(value = "userId") String userId);
    
    @GetMapping("/mobile/{mobile}")
    SysUser findUserByMobile(@RequestParam(value = "mobile") String mobile);
}
