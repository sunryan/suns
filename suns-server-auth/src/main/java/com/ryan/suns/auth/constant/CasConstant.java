package com.ryan.suns.auth.constant;

/**
 * @author lr
 * @date 2018/2/1
 */
public class CasConstant {
    
    /**
     * cas server地址
     */
    public static final String CAS_SERVER_URL_PREFIX = "https://localhost:8103/cas";
    
    /**
     * Cas登录页面地址
     */
    public static final String CAS_LOGIN_URL = CAS_SERVER_URL_PREFIX + "/login";
    
    /**
     * Cas登出页面地址
     */
    public static final String CAS_LOGOUT_URL = CAS_SERVER_URL_PREFIX + "/logout";
    
    /**
     * 当前工程对外提供的服务地址
     */
    public static final String SHIRO_SERVER_URL_PREFIX = "http://localhost:8103/auth";
    
    /**
     * casFilter UrlPattern
     */
    public static final String CAS_FILTER_URL_PATTERN = "/cas";
    
    /**
     * 登录地址
     */
    public static final String LOGIN_URL = CAS_LOGIN_URL + "?service=" + SHIRO_SERVER_URL_PREFIX + CAS_FILTER_URL_PATTERN;
    
    /**
     * 登出地址（casserver启用service跳转功能，需在webapps\cas\WEB-INF\cas.properties文件中启用cas.logout.followServiceRedirects=true）
     */
    public static final String LOGOUT_URL = CAS_LOGOUT_URL + "?service="+ SHIRO_SERVER_URL_PREFIX;
    /**
     * 登录成功地址
     */
    public static final String LOGIN_SUCCESS_URL = "/home";
    /**
     * 权限认证失败跳转地址
     */
    public static final String ERROR_403 = "/error/403.html";
}
