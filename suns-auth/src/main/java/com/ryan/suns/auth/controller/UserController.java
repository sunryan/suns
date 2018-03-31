package com.ryan.suns.auth.controller;

import com.ryan.suns.api.feign.admin.UserClient;
import com.ryan.suns.common.constant.SecurityConstants;
import com.ryan.suns.common.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author lr
 * @date 2017/10/26
 */
@RestController
public class UserController {
    
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    @Autowired
    private UserClient userClient;
    
    /**
     * 未登录json提示
     * @return
     * @throws IOException
     */
    @RequestMapping("/authentication/require")
    public ResponseEntity require() {
        return new ResponseEntity<R<String>>(new R().fail("请登陆"), HttpStatus.UNAUTHORIZED);
    }


    @RequestMapping("/user")
    public Object user(Authentication authentication) {
        return authentication.getPrincipal();
    }

    /**
     * 清除Redis中 accesstoken refreshtoken
     *
     * @param accesstoken  accesstoken
     * @param refreshToken refreshToken
     * @return true/false
     */
    @PostMapping("/removeToken")
    @CacheEvict(value = SecurityConstants.TOKEN_USER_DETAIL, key = "#accesstoken")
    public R<Boolean> removeToken(String accesstoken, String refreshToken) {
        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
        tokenStore.removeRefreshToken(refreshToken);
        tokenStore.removeAccessToken(accesstoken);
        return new R<>(Boolean.TRUE);
    }
}