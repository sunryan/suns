package com.ryan.suns.gateway.jwt;

import com.ryan.suns.common.util.CookieUtil;
import com.ryan.suns.gateway.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * @author lr
 * @date 2018/2/9
 */
@Component
public class JwtUtil {
    
    private static RsaKeyHelper rsaKeyHelper = new RsaKeyHelper();
    
    @Autowired
    private JwtConfig jwtConfig;
    
    
    public static final long EXPIRATIONTIME = 5 * 24 * 60 * 60 * 1000;     // 5天
    public static final String TOKEN_PREFIX = "Bearer";        // Token前缀
    public static final String HEADER_STRING = "Authorization";// 存放Token的Header Key
    
    /**
     *  JWT生成方法
     * @param response
     * @param username
     * @return
     * @throws Exception
     */
  
    public String addAuthentication(HttpServletResponse response, String username) throws Exception {
        
        // 生成JWT
        String jwtToken = Jwts.builder()
                // 保存权限（角色）
                .claim("authorities", "HELLO,ROLE_WORLD")
                // 用户名写入标题
                .setSubject(username)
                // 有效期设置
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                // 签名设置
                .signWith(SignatureAlgorithm.RS256, rsaKeyHelper.getPrivateKey(jwtConfig.getPriKeyPath()))
                .compact();
        
        return jwtToken;
    }
    
    /**
     *  JWT验证方法
     * @param request
     * @return
     * @throws Exception
     */
    public Authentication getAuthentication(HttpServletRequest request) throws Exception {
        // 从Header中拿到token
        String token = request.getHeader(HEADER_STRING);
        if(token == null){
            Cookie cookie = CookieUtil.getCookieByName(request, "suns_token");
            if(cookie != null){
                token = cookie.getValue();
            }
        }else if(token.startsWith(TOKEN_PREFIX)){
            token = token.replace(TOKEN_PREFIX, "");
        }
        
        if (token != null) {
            // 解析 Token
            Claims claims = Jwts.parser()
                    // 验签
                    .setSigningKey(rsaKeyHelper.getPublicKey(jwtConfig.getPubKeyPath()))
                    // 去掉 Bearer
                    .parseClaimsJws(token)
                    .getBody();
            
            // 拿用户名
            String user = claims.getSubject();
            
            // 得到 权限/角色
            List<GrantedAuthority> authorities =  AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("authorities"));
            
            // 返回验证令牌
            return user != null ? new UsernamePasswordAuthenticationToken(user, null, authorities) : null;
        }
        return null;
    }
}
