package com.ryan.suns.auth.controller;

import com.ryan.suns.common.constant.RedisConstants;
import com.ryan.suns.common.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author lr
 * @date 2017/10/26
 */
@RestController
@Slf4j
public class UserController {
    
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    @Autowired
    private RedisTemplate redisTemplate;
    
    /**
     * 未登录json提示
     * @return
     * @throws IOException
     */
    @RequestMapping("/form/require")
    public ResponseEntity require() {
        return new ResponseEntity<R<String>>(new R().fail("请登陆"), HttpStatus.UNAUTHORIZED);
    }


    @RequestMapping("/user")
    public Object user(Authentication authentication) {
        return authentication.getPrincipal();
    }
    
    /**
     * 注销
     * @param accesstoken
     * @param refreshToken
     * @return
     */
    @RequestMapping("/form/logout")
    public R<Boolean> logout(Authentication authentication, String accesstoken, String refreshToken) {
        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
        tokenStore.removeRefreshToken(refreshToken);
        tokenStore.removeAccessToken(accesstoken);
        log.info("注销");
        redisTemplate.opsForHash().delete(RedisConstants.USER_PERMISSION_KEY, authentication.getName());
        return new R<>(Boolean.TRUE);
    }
}