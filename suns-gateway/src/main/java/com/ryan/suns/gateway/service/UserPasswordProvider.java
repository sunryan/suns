package com.ryan.suns.gateway.service;

import cn.hutool.core.util.StrUtil;
import com.ryan.suns.api.feign.user.ResourcesClient;
import com.ryan.suns.api.feign.user.UserClient;
import com.ryan.suns.common.model.auth.Resources;
import com.ryan.suns.common.model.auth.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义身份认证验证组件
 * @author lr
 * @date 2018/2/9
 */
public class UserPasswordProvider implements AuthenticationProvider {
    
    @Resource
    private UserClient userClient;
    @Resource
    private ResourcesClient resourcesClient;
    @Resource
    private PasswordEncoder passwordEncoder;
    
 /*   public UserPasswordProvider(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }
*/
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取认证的用户名 & 密码
        String name = authentication.getName();
        String password = authentication.getCredentials() == null ? null : authentication.getCredentials().toString();
        
        if(StrUtil.isEmpty(name) || StrUtil.isEmpty(password)){
            throw new BadCredentialsException("输入用户名密码");
        }
        User user = userClient.selectByUsername(name);
        if(user == null){
            throw new BadCredentialsException("用户不存在");
        }
        if(! passwordEncoder.matches(password, user.getPassword())){
            throw new BadCredentialsException("密码错误");
        }
        List<Resources> resourceList =  resourcesClient.loadUserResources(user.getId());
        // 这里设置权限和角色
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        for(Resources resources : resourceList){
            authorities.add( new SimpleGrantedAuthority(resources.getResurl()));
        }
        // 生成令牌
        Authentication auth = new UsernamePasswordAuthenticationToken(name, password, authorities);
        return auth;
    }
    
    // 是否可以提供输入类型的认证服务
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
