package com.ryan.suns.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @author lr
 * @date 2018/3/28.
 */
public class SunsOAuth2Exception extends OAuth2Exception {
    
    public String meg;
    
    public SunsOAuth2Exception(String msg, Throwable t) {
        super(msg, t);
        this.meg = msg;
    }
    
    public String getOAuth2ErrorCode() {
        return "登陆失败";
    }
    
    public int getHttpErrorCode() {
        return HttpStatus.UNAUTHORIZED.value();
    }
    
    public String getMeg() {
        return meg;
    }
    
    public void setMeg(String meg) {
        this.meg = meg;
    }
}
