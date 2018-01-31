package com.ryan.suns.auth.config.shiro;

/**
 * @author lr
 * @date 2018/1/23
 */

import com.ryan.suns.api.feign.user.ResourcesClient;
import com.ryan.suns.auth.config.redis.RedisCacheManager;
import com.ryan.suns.auth.config.redis.RedisSessionDao;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author lr
 * @date 2018/1/23
 */
@Configuration
public class ShiroConfig {



    @Value("${ryan.suns.auth.loginUrl}")
    private String loginUrl;

    @Value("${ryan.suns.auth.successUrl}")
    private String successUrl;

    @Value("${ryan.suns.auth.unauthorizedUrl}")
    private String unauthorizedUrl;

    @Autowired
    private ResourcesClient resourcesClient;


    @Bean
    public RedisSessionDao sessionDAO() {
        return new RedisSessionDao();
    }

    @Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCacheManager(redisCacheManager());
        return myShiroRealm;
    }
    
    @Bean
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
    
    @Bean
    public RedisCacheManager redisCacheManager() {
        return new RedisCacheManager();
    }
    
    @Bean
    public SessionManager sessionManager() {
        System.out.println("===========================sessionManager start");
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(sessionDAO());
        // 设置全局session超时时间
        sessionManager.setGlobalSessionTimeout(RedisSessionDao.EXPIRE_TIME);
        // 删除过期的session
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setCacheManager(redisCacheManager());
        System.out.println("===========================sessionManager end");
        return sessionManager;
    }
    
    /**
     * 安全管理器
     * @return
     */
    @Bean
    public SecurityManager securityManager() {
        System.out.println("===========================securityManager start");
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //注入realm.
        securityManager.setRealm(myShiroRealm());
        // 注入session管理器
        securityManager.setSessionManager(sessionManager());
        // 注入缓存管理器
        securityManager.setCacheManager(redisCacheManager());
        System.out.println("===========================securityManager end");
        return securityManager;
    }
    
    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager());
        return new AuthorizationAttributeSourceAdvisor();
    }
    
    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }
    
    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     *  所以我们需要修改下doGetAuthenticationInfo中的代码;
     * ）
     * 可以扩展凭证匹配器，实现 输入密码错误次数后锁定等功能，下一次
     * @return
     */
    @Bean(name="credentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
        //storedCredentialsHexEncoded默认是true，此时用的是密码加密用的是Hex编码；false时用Base64编码
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        
        return hashedCredentialsMatcher;
    }
    
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        System.out.println("===========================shiro-filter start");
        //定义ShiroFilter工厂
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 注入安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //未授权界面跳转403
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        //定义拦截器
       
        shiroFilterFactoryBean.setFilterChainDefinitionMap(resourcesClient.loadShiroFilter());
        System.out.println("===========================shiro-filter end");
        return shiroFilterFactoryBean;
    }
}