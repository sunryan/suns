package com.ryan.suns.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.ryan.suns.common.util.CookieUtil;
import com.ryan.suns.gateway.jwt.JwtUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证token
 * @author lr
 * @date 2018/2/3.
 */
@Slf4j
public class TokenFilter extends ZuulFilter {
    
    @Override
    public String filterType() {
        return "pre";
    }
    
    @Override
    public int filterOrder() {
        return 0;
    }
    
    @Override
    public boolean shouldFilter() {
        return true;
    }
    
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();
        
        log.info("send {} request to {}", request.getMethod(), request.getRequestURL().toString());
        Cookie cookie = CookieUtil.getCookieByName(request, "suns_token");
        if(cookie != null){
            response.addHeader(JwtUtil.HEADER_STRING, JwtUtil.TOKEN_PREFIX + " " + cookie.getValue());
        }
        return null;
    }
}
