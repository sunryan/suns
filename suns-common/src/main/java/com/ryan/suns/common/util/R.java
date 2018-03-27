package com.ryan.suns.common.util;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lr
 * @date 2018/3/22.
 */
@Data
public class R<T>  implements Serializable {


    private static final long serialVersionUID = 1L;

    public static final boolean SUCCESS = true;

    public static final boolean FAIL = false;
    

    private String msg = "success";

    private boolean success = SUCCESS;

    private T data;

    public R() {
        super();
    }

    public R(T data) {
        super();
        this.data = data;
    }

    public R(Throwable e) {
        super();
        this.msg = e.getMessage();
        this.success = FAIL;
    }
    
    public R OK(T data) {
        this.data = data;
        return this;
    }
    
    public R fail(String msg) {
        this.msg = msg;
        this.success = FAIL;
        return this;
    }

}
