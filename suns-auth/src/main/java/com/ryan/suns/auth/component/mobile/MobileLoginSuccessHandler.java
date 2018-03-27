package com.ryan.suns.auth.component.mobile;

import com.ryan.suns.auth.component.SunsAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * @author lr
 * @date 2018/1/8
 * 手机号登录成功，返回oauth token
 */
@Component("mobileLoginSuccessHandler")
public class MobileLoginSuccessHandler extends SunsAuthenticationSuccessHandler {
    
    protected String getGrantType(){
        return MobileAuthenticationFilter.SPRING_SECURITY_FORM_MOBILE_KEY;
    }

}
