package com.ryan.suns.auth.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ryan.suns.common.ResponseData;
import com.ryan.suns.common.constant.CommonConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lr
 * @date 2018/3/26
 */
@Component("logoutSuccessHandler")
public class SunsLogoutSuccessHandler implements LogoutSuccessHandler {
    
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private ObjectMapper objectMapper;
    
    
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        
        logger.info("退出成功");
        response.setCharacterEncoding(CommonConstant.UTF8);
        response.setContentType(CommonConstant.CONTENT_TYPE);
        response.getWriter().write(objectMapper.writeValueAsString(new ResponseData()));
        
    }
}
