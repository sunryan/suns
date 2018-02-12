package com.ryan.suns.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author lr
 * @date 2018/2/8
 */
public class CookieUtil {
    
    
    /**
     * 设置cookie
     * @param response
     * @param name cookie名字
     * @param value  cookie值
     */
    public static void addCookie(HttpServletResponse response, String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
    
    /**
     * 设置cookie
     * @param response
     * @param name cookie名字
     * @param value  cookie值
     * @param maxAge cookie生命周期 以秒为单位 maxAge=0表示随浏览器关闭而消失的cookie
     */
    public static void addCookie(HttpServletResponse response, String name,String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        if (maxAge > 0) cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }
    
    /**
     * 根据名字获取cookie
     * @param request
     * @param name cookie名字
     */
    public static Cookie getCookieByName(HttpServletRequest request, String name) {
        Map<String, Cookie> cookieMap = ReadCookieMap(request);
        if (cookieMap.containsKey(name)) {
            Cookie cookie = (Cookie) cookieMap.get(name);
            return cookie;
        } else {
            return null;
        }
    }
    
    /**
     * 将cookie封装到map
     * @author kjl
     * @date 2015-9-28
     */
    private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }
}
