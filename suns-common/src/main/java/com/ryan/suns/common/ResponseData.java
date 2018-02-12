package com.ryan.suns.common;


import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @author lr
 * @date 2018/2/12
 */
@Data
public class ResponseData {
    
    private String code;
    private boolean success;
    private String message;
    private Object data;
    
    
    public static ResponseData OK() {
        return new ResponseData();
    }
    
    public static ResponseData OK(String message, Object data) {
        return new ResponseData(ResultCode.SUCCESS, message, data);
    }
    
    
    public ResponseData() {
        this.setCode(ResultCode.SUCCESS.val());
        this.setSuccess(ResultCode.SUCCESS.success());
        this.setMessage("成功");
    }
    
    public ResponseData(ResultCode code) {
        this.setCode(code.val());
        this.setSuccess(code.success());
        this.setMessage(code.msg());
    }
    
    public ResponseData(ResultCode code, String message) {
        this.setCode(code.val());
        this.setSuccess(code.success());
        this.setMessage(message);
    }
    
    public ResponseData(ResultCode code, String message, Object data) {
        this.setCode(code.val());
        this.setSuccess(code.success());
        this.setMessage(message);
        this.setData(data);
    }
    
    public String toString() {
        
        JSONObject json = new JSONObject();
        try {
            json.put("code", code);
            json.put("success", success);
            json.put("message", message);
            json.put("data", data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        return json.toString();
    }
    
    
}
