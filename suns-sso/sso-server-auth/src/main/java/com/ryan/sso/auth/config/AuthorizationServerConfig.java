package com.ryan.sso.auth.config;


import com.ryan.sso.auth.jwt.SunsJwtAccessTokenConverter;
import com.ryan.sso.auth.service.DomainUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;



/**
 *
 * 授权服务器配置
 * Configuration注解，表明这是一个beans上下文配置Spring容器
 * EnableAuthorizationServer注解开启Auth授权服务器
 *
 * @author lr
 * @date 2018/1/18
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisConnectionFactory connectionFactory;
    @Autowired
    private DomainUserDetailsService userDetailsService;


    /**
     * 声明TokenStore实现 (redis存储)
     * @return
     */
//    @Bean
    public TokenStore redisTokenStore() {
        return new RedisTokenStore(connectionFactory);
    }
    
    
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }
    
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
      
        clients.inMemory()
                .withClient("client")
                .secret("secret")
                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
                .scopes("app")
            .and()
                .withClient("webapp")
                .scopes("xx")
                .authorizedGrantTypes("implicit","password");
    }
    
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //实现token生成 并持久化到数据库中
        endpoints
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
        //jwt
        endpoints.accessTokenConverter(accessTokenConverter());
        endpoints.tokenStore(jwtTokenStore());
//        endpoints.accessTokenConverter(accessTokenConverter());

        // 配置TokenServices参数(控制token 的失效时间等信息)
       /* DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(endpoints.getTokenStore());
        tokenServices.setSupportRefreshToken(false);
        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
        tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
        tokenServices.setAccessTokenValiditySeconds( (int) TimeUnit.DAYS.toSeconds(30));
        endpoints.tokenServices(tokenServices);*/
    }

    @Bean(name = "jwtAccessTokenConverter")
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new SunsJwtAccessTokenConverter();
        accessTokenConverter.setSigningKey("123");// 测试用,资源服务使用相同的字符达到一个对称加密的效果,生产时候使用RSA非对称加密方式
        return accessTokenConverter;
    }

    /**
     * token store
     *
     * @return
     */
    @Bean(name = "jwtTokenStore")
    public TokenStore jwtTokenStore() {
        TokenStore tokenStore = new JwtTokenStore(accessTokenConverter());
        return tokenStore;
    }
}
