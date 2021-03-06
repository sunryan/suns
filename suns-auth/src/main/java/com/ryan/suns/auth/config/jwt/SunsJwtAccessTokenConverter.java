package com.ryan.suns.auth.config.jwt;

import com.ryan.suns.api.feign.admin.UserClient;
import com.ryan.suns.auth.component.UserDetailsImpl;
import com.ryan.suns.common.model.admin.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Autowired
    private UserClient userClient;
    
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        String username = authentication.getUserAuthentication().getName();
        // 与登录时候放进去的UserDetail实现类一直查看link{SecurityConfiguration}
        UserDetailsImpl user = (UserDetailsImpl) authentication.getUserAuthentication().getPrincipal();
    
        SysUser sysUser = userClient.findUserByUsername(username);
        /** 自定义一些token属性 ***/
        final Map<String, Object> additionalInformation = new HashMap<>();
        additionalInformation.put("username", username);
        if(sysUser !=  null){
            additionalInformation.put("avatar", sysUser.getAvatar());
            additionalInformation.put("roles", sysUser.getRoles());
        }
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
        OAuth2AccessToken enhancedToken = super.enhance(accessToken, authentication);
        return enhancedToken;
    }
    
}
