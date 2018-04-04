package com.ryan.suns.common.model.enums.suport;

/**
 * @author lr
 * @date 2018/4/4
 */
public class NoMatchedEnumException extends RuntimeException{
    
    public NoMatchedEnumException(String msg) {
        super(msg);
    }
}
