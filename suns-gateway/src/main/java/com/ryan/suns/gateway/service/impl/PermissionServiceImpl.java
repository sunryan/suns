package com.ryan.suns.gateway.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.ryan.suns.api.feign.admin.MenuClient;
import com.ryan.suns.common.constant.RedisConstants;
import com.ryan.suns.common.model.admin.SysMenu;
import com.ryan.suns.gateway.service.PermissionService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * TODO 每个api都要配置权限比较麻烦，后续改成查询所有配置api的menu, 所有匹配上的api拥有的角色符合或则没有匹配的api通过
 * @author lr
 * @date 2017/10/28
 */
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    
    @Autowired
    private MenuClient menuClient;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        //用户拥有权限
        List<SimpleGrantedAuthority> grantedAuthorityList = (List<SimpleGrantedAuthority>) authentication.getAuthorities();
        boolean hasPermission = false;

        if (principal != null) {
            // TODO 暂时方便
            if(!hasPermission){
                return true;
            }
            //AnonymousAuthenticationToken 无权限
            if(AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())){
                return hasPermission;
            }
            if (CollectionUtil.isEmpty(grantedAuthorityList)) {
                return hasPermission;
            }
            Set<SysMenu> urls = (Set<SysMenu>) redisTemplate.opsForHash().get(RedisConstants.USER_PERMISSION_KEY, authentication.getName());
            
            if(urls == null){
                urls = new HashSet<>();
                for (SimpleGrantedAuthority authority : grantedAuthorityList) {
                    urls.addAll(menuClient.findMenuByRoleCode(authority.getAuthority()));
                }
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
