package com.ryan.suns.auth.logic;

import cn.hutool.core.util.StrUtil;
import com.ryan.suns.api.feign.user.ResourcesClient;
import com.ryan.suns.common.model.auth.Resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lr
 * @date 2018/2/1
 */
@Component
public class ShiroLogic {
    
    
    @Autowired
    private ResourcesClient resourcesClient;
    
    /**
     * 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边
     *  roles[admin,user] 拥有角色访问
     *  perms[file:edit] 拥有资源访问
     *  authc:所有url都必须认证通过才可以访问;
     *  anon:所有url都都可以匿名访问
     */
    public Map<String, String> loadShiroFilterChain(){
        Map<String, String> filterChainDefinitionMap = new HashMap<>();
        // 静态资源部受权限限制
        filterChainDefinitionMap.put("/css/**","anon");
        filterChainDefinitionMap.put("/js/**","anon");
        filterChainDefinitionMap.put("/img/**","anon");
        filterChainDefinitionMap.put("/static/**","anon");
        
        //加载资源环境过滤
        
        List<Resources> resourcesList = resourcesClient.loadShiroFilter();
        for(Resources resources : resourcesList){
            if (StrUtil.isNotEmpty(resources.getResurl())) {
                String permission = "perms[" + resources.getResurl()+ "]";
                filterChainDefinitionMap.put(resources.getResurl(), permission);
            }
        }
        filterChainDefinitionMap.put("/**", "authc");
        return filterChainDefinitionMap;
    }
}
