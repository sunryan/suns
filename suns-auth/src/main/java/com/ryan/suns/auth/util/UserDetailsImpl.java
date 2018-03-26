package com.ryan.suns.auth.util;

import com.ryan.suns.common.constant.SecurityConstants;
import com.ryan.suns.common.model.user.SysRole;
import com.ryan.suns.common.model.user.SysUser;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author lr
 * @date 2017/10/29
 */
@Getter
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private List<SysRole> roleList;

    public UserDetailsImpl(SysUser sysUser) {
        this.username = sysUser.getUsername();
        this.password = sysUser.getPassword();
        this.roleList = sysUser.getRoleList();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(roleList)){
            for (SysRole role : roleList) {
                authorityList.add(new SimpleGrantedAuthority(role.getRoleCode()));
            }
        }
        authorityList.add(new SimpleGrantedAuthority(SecurityConstants.BASE_ROLE));
        return authorityList;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
