package com.ryan.sso.auth.jwt;

import com.ryan.suns.common.model.auth.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lr
 * @date 2018/2/3.
 */
public class SunsJwtAccessTokenConverter extends JwtAccessTokenConverter {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        String userName = authentication.getUserAuthentication().getName();
        // 与登录时候放进去的UserDetail实现类一直查看link{SecurityConfiguration}
        User user = (User) authentication.getUserAuthentication().getPrincipal();
        /** 自定义一些token属性 ***/
        final Map<String, Object> additionalInformation = new HashMap<>();
        additionalInformation.put("userName", userName);
        additionalInformation.put("roles", user.getRoleList());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
        OAuth2AccessToken enhancedToken = super.enhance(accessToken, authentication);
        System.out.println("tkoen =" + enhancedToken.getValue());
        return enhancedToken;
    }
}
