package com.ryan.suns.auth.component.mobile.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author lr
 * @date 2018/3/27
 */
public class MobileNotFundException extends AuthenticationException {
    
    public MobileNotFundException(String message){
        super(message);
    }
}
