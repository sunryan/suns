package com.ryan.suns.auth.component.mobile;

import cn.hutool.http.HttpStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ryan.suns.auth.component.mobile.exception.MobileNotFundException;
import com.ryan.suns.common.constant.CommonConstant;
import com.ryan.suns.common.util.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lr
 * @date 2018/3/27
 */
@Component("mobileLoginFailureHandler")
public class MobileLoginFailureHandler implements AuthenticationFailureHandler {
    
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        
        String mes = "手机登录失败";
        
        if(exception instanceof MobileNotFundException){
            mes = exception.getMessage();
        }
        response.setCharacterEncoding(CommonConstant.UTF8);
        response.setContentType(CommonConstant.CONTENT_TYPE);
        response.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
        response.getWriter().write(objectMapper.writeValueAsString(new R().fail(mes)));
        
        logger.info(mes);
    }
}
