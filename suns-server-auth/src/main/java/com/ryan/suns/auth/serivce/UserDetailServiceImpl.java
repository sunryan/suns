package com.ryan.suns.auth.serivce;

import com.ryan.suns.auth.util.UserDetailsImpl;
import com.ryan.suns.common.constant.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

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
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {

        return new UserDetailsImpl("admin",passwordEncoder.encode("123456"));
    }

    /**
     * @param userId the user ID used to lookup the user details
     * @return the SocialUserDetails requested
     * @see UserDetailsService#loadUserByUsername(String)
     */
    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
//        UserVo userVo = userService.findUserByOpenId(userId);
//        if (userVo == null){
//            throw new UsernameNotFoundException("用户未绑定");
//        }
//        List<GrantedAuthority> authorityList = new ArrayList<>();
//        for (SysRole role : userVo.getRoleList()) {
//            authorityList.add(new SimpleGrantedAuthority(role.getRoleCode()));
//        }
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority(SecurityConstants.BASE_ROLE));
        return new SocialUser("admin", "123456", authorityList );
    }
}
