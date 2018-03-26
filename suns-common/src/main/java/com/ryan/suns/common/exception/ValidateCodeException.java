package com.ryan.suns.common.exception;

/**
 * 验证码校验异常
 * @author lr
 * @date 2018/3/22.
 */
public class ValidateCodeException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -7285211528095468156L;

    public ValidateCodeException(String msg) {
        super(msg);
    }

}
