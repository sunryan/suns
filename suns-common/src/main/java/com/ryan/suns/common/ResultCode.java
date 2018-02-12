package com.ryan.suns.common;

/**
 * @author lr
 * @date 2018/2/12
 */
public enum ResultCode {
    
    /** 成功 */
    SUCCESS("200", "成功", true),
    
    /** 操作失败 */
    FAIL("205", "操失败", false),
    
    /** 数据已存在 */
    SUCCESS_IS_HAVE("208", "数据已存在", false),
    
    /** 没有结果 */
    NOT_DATA("911", "没有结果", false),
    
    /** 没有登录 */
    NOT_LOGIN("600", "没有登录", false),
    
    /** 发生异常 */
    EXCEPTION("401", "发生异常", false),
    
    /** 系统错误 */
    SYS_ERROR("402", "系统错误", false),
    
    /** 参数错误 */
    PARAMS_ERROR("403", "参数错误", false),
    
    /** 不支持或已经废弃 */
    NOT_SUPPORTED("410", "不支持或已经废弃", false),
    
    /** AuthCode错误 */
    INVALID_AUTHCODE("444", "无效的AuthCode", false),
    
    /** 太频繁的调用 */
    TOO_FREQUENT("445", "太频繁的调用", false),
    
    /** 未知的错误 */
    UNKNOWN_ERROR("499", "未知错误", false),
    
    /** 未设置方法 */
    NOT_METHOD("4004", "未设置方法", false);
    
    private ResultCode(String val, String msg, boolean success){
        this.val = val;
        this.msg = msg;
        this.success = success;
    }
    
    public String val() {
        return val;
    }
    
    public String msg() {
        return msg;
    }
    
    public boolean success() {
        return success;
    }
    
    private String val;
    private String msg;
    private boolean success;
}
