package com.ryan.suns.common.exception;

/**
 * @author lr
 * @date 2018/3/22.
 */
public class SunsDeniedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SunsDeniedException() {
    }

    public SunsDeniedException(String message) {
        super(message);
    }

    public SunsDeniedException(Throwable cause) {
        super(cause);
    }

    public SunsDeniedException(String message, Throwable cause) {
        super(message, cause);
    }

    public SunsDeniedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
