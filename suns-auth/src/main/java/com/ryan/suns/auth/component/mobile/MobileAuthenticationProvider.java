package com.ryan.suns.auth.component.mobile;

import com.ryan.suns.api.feign.user.UserClient;
import com.ryan.suns.auth.util.UserDetailsImpl;
import com.ryan.suns.common.model.user.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * @author lr
 * @date 2018/1/9
 * 手机号登录校验逻辑
 */
public class MobileAuthenticationProvider implements AuthenticationProvider {
    
    @Autowired
    private UserClient userClient;
    
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        MobileAuthenticationToken mobileAuthenticationToken = (MobileAuthenticationToken) authentication;
        SysUser sysUser = userClient.findUserByMobile((String) mobileAuthenticationToken.getPrincipal());

        UserDetailsImpl userDetails = buildUserDeatils(sysUser);
        if (userDetails == null) {
            throw new InternalAuthenticationServiceException("手机号不存在:" + mobileAuthenticationToken.getPrincipal());
        }

        MobileAuthenticationToken authenticationToken = new MobileAuthenticationToken(userDetails, userDetails.getAuthorities());
        authenticationToken.setDetails(mobileAuthenticationToken.getDetails());
        return authenticationToken;
    }

    private UserDetailsImpl buildUserDeatils(SysUser sysUser) {
        return new UserDetailsImpl(sysUser);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return MobileAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
