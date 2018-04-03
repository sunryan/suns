package com.ryan.suns.common;

import com.ryan.suns.common.util.R;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author lr
 * @date 2018/4/3
 */
public class BaseControl {

    protected ResponseEntity ok(){
        return new ResponseEntity<R<String>>(new R(), HttpStatus.OK);
    }
    
    protected ResponseEntity ok(Object data){
        return new ResponseEntity<R<String>>(new R(data), HttpStatus.OK);
    }
    
    protected ResponseEntity fail(){
        return new ResponseEntity<R<String>>(new R().fail("操作失败"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    protected ResponseEntity fail(String msg){
        return new ResponseEntity<R<String>>(new R().fail(msg), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    
}
