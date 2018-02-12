package com.ryan.suns.api.feign.user;

import com.ryan.suns.api.feign.config.FeignConfiguration;
import com.ryan.suns.common.model.auth.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author lr
 * @date 2018/2/1
 */
@FeignClient(value = "suns-server-user", path = "/api/user", configuration = FeignConfiguration.class)
public interface UserClient {
    
    
    @GetMapping("/selectByUsername")
    User selectByUsername(@RequestParam("username") String username);

}
