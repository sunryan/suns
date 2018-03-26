package com.ryan.suns.gateway.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.ryan.suns.api.feign.user.MenuClient;
import com.ryan.suns.common.model.user.SysMenu;
import com.ryan.suns.gateway.service.PermissionService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author lr
 * @date 2017/10/28
 */
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    
    @Autowired
    private MenuClient menuClient;

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        //用户拥有权限
        List<SimpleGrantedAuthority> grantedAuthorityList = (List<SimpleGrantedAuthority>) authentication.getAuthorities();
        boolean hasPermission = false;

        if (principal != null) {
            if("anonymousUser".equalsIgnoreCase((String)principal)){
                return hasPermission;
            }
            if (CollectionUtil.isEmpty(grantedAuthorityList)) {
                return hasPermission;
            }
    
            Set<SysMenu> urls = new HashSet<>();
            for (SimpleGrantedAuthority authority : grantedAuthorityList) {
                urls.addAll(menuClient.findMenuByRoleCode(authority.getAuthority()));
            }
    
            for (SysMenu menu : urls) {
                if (StringUtils.isNotEmpty(menu.getUrl()) && antPathMatcher.match(menu.getUrl(), request.getRequestURI())
                        && request.getMethod().equalsIgnoreCase(menu.getMethod())) {
                    hasPermission = true;
                    break;
                }
            }
        }
        return hasPermission;
    }
}
