package com.ryan.suns.api.feign.admin;

import com.ryan.suns.api.feign.config.FeignConfiguration;
import com.ryan.suns.common.model.admin.SysUser;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    @RequestMapping(value = "/name/{username}", method = RequestMethod.GET)
    SysUser findUserByUsername(@PathVariable("username") String username);
    
    /**
     * 根据 userId 获取用户信息和用户角色list
     * @param userId
     * @return
     */
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    SysUser findUserByUserId(@PathVariable("userId") String userId);
    
    /**
     * 根据 mobile 获取用户信息和用户角色list
     * @param mobile
     * @return
     */
    @RequestMapping(value = "/mobile/{mobile}", method = RequestMethod.GET)
    SysUser findUserByMobile(@PathVariable("mobile") String mobile);
    
}
