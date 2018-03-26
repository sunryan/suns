package com.ryan.suns.auth.config.jwt;

import com.ryan.suns.auth.component.UserDetailsImpl;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lr
 * @date 2018/3/26
 */
public class SunsJwtAccessTokenConverter extends JwtAccessTokenConverter {
    
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        String userName = authentication.getUserAuthentication().getName();
        // 与登录时候放进去的UserDetail实现类一直查看link{SecurityConfiguration}
        UserDetailsImpl user = (UserDetailsImpl) authentication.getUserAuthentication().getPrincipal();
        /** 自定义一些token属性 ***/
        final Map<String, Object> additionalInformation = new HashMap<>();
        additionalInformation.put("userName", userName);
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
        OAuth2AccessToken enhancedToken = super.enhance(accessToken, authentication);
        return enhancedToken;
    }
}
