package com.ryan.suns.auth.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ryan.suns.common.ResponseData;
import com.ryan.suns.common.ResultCode;
import com.ryan.suns.common.constant.CommonConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lr
 * @date 2018/3/26
 */
@Component("authenctiationFailureHandler")
public class SunsAuthenctiationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        
        logger.info("登录失败");
        
        response.setCharacterEncoding(CommonConstant.UTF8);
        response.setContentType(CommonConstant.CONTENT_TYPE);
        response.getWriter().write(objectMapper.writeValueAsString(new ResponseData(ResultCode.FAIL)));
        
    }
}
