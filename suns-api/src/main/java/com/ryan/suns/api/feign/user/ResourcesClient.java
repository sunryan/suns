package com.ryan.suns.api.feign.user;

import com.ryan.suns.api.feign.config.FeignConfiguration;
import com.ryan.suns.common.model.auth.Resources;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Created by ryan on 2018/1/31.
 */
@FeignClient(value = "suns-server-user", path = "/api/resources", configuration = FeignConfiguration.class)
public interface ResourcesClient {

    /**
     * 加载shiro 资源权限过滤器
     * @return
     */
    @GetMapping("/loadShiroFilter")
    List<Resources> loadShiroFilter();

    @GetMapping("/loadUserResources")
    List<Resources> loadUserResources(@RequestParam("userId") Integer userId);
}
