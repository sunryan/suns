package com.ryan.suns.auth.serivce;

import com.ryan.suns.api.feign.admin.UserClient;
import com.ryan.suns.auth.component.UserDetailsImpl;
import com.ryan.suns.common.constant.SecurityConstants;
import com.ryan.suns.common.model.admin.SysRole;
import com.ryan.suns.common.model.admin.SysUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lr
 * @date 2017/10/26
 * <p>
 */
@Service("userDetailService")
public class UserDetailServiceImpl implements UserDetailsService,SocialUserDetailsService, Serializable {

    @Autowired
    private UserClient userClient;
    
    @Override
    public UserDetailsImpl loadUserByUsername(String username)  {
    
        if(StringUtils.isEmpty(username)){
            throw new BadCredentialsException("用户不允许为空");
        }
        SysUser user = userClient.findUserByUsername(username);
        if(user == null){
            throw new BadCredentialsException("用户不存在");
        }
        return new UserDetailsImpl(user);
    }

    /**
     * @param userId the admin ID used to lookup the admin details
     * @return the SocialUserDetails requested
     * @see UserDetailsService#loadUserByUsername(String)
     */
    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        SysUser user = userClient.findUserByUserId(userId);
        if (user == null){
            throw new UsernameNotFoundException("用户未绑定");
        }
        List<GrantedAuthority> authorityList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(user.getRoleList())){
            for (SysRole role : user.getRoleList()) {
                authorityList.add(new SimpleGrantedAuthority(role.getRoleCode()));
            }
        }else{
            authorityList.add(new SimpleGrantedAuthority(SecurityConstants.BASE_ROLE));
        }
        return new SocialUser(user.getUsername(), user.getPassword(), authorityList );
    }
}
