package com.ryan.suns.gateway.filter;

import com.ryan.suns.gateway.jwt.JwtUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author lr
 * @date 2018/2/9
 */
public class JWTAuthenticationFilter extends GenericFilterBean {
    
    @Resource
    private JwtUtil jwtUtil;
    
    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain)
            throws IOException, ServletException {
        Authentication authentication = null;
        try {
            authentication = jwtUtil.getAuthentication((HttpServletRequest)request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        SecurityContextHolder.getContext()
                .setAuthentication(authentication);
        filterChain.doFilter(request,response);
    }
}
