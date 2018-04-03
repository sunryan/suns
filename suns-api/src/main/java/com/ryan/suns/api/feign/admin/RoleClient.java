package com.ryan.suns.api.feign.admin;

import com.ryan.suns.api.feign.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author lr
 * @date 2018/4/3
 */
@FeignClient(value = "suns-server-admin", path = "/role", configuration = FeignConfiguration.class)
public interface RoleClient {
}
