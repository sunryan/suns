package com.ryan.suns.gateway.filter;

import com.ryan.suns.common.ResponseData;
import com.ryan.suns.common.ResultCode;
import com.ryan.suns.gateway.jwt.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lr
 * @date 2018/2/9
 */
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {
   
    @Resource
    private JwtUtil jwtUtil;
    
    public JWTLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url, "POST"));
        setAuthenticationManager(authManager);
    }
    
    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException, IOException, ServletException {
    
        // 返回一个验证令牌
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getParameter("username"),
                        req.getParameter("password")
                )
        );
    }
    
    @Override
    protected void successfulAuthentication(
            HttpServletRequest req,
            HttpServletResponse res, FilterChain chain,
            Authentication auth) throws IOException, ServletException {
    
        String jwtToken = null;
        try {
            jwtToken = jwtUtil.addAuthentication(res, auth.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 将 JWT 写入 body
        res.addCookie(new Cookie("suns_token", jwtToken));
        res.setContentType("application/json; charset=UTF-8");
        res.setStatus(HttpServletResponse.SC_OK);
        res.getWriter().println(ResponseData.OK(jwtToken, null));
     
    }
    
    
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        
        response.setContentType("application/json; charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(new ResponseData(ResultCode.FAIL, failed.getMessage(), null));
    }
}