package com.ryan.suns.common.model.enums.suport;

/**
 * @author lr
 * @date 2018/4/6.
 */
public class EnumUtil {
    
    /**
     * 返回指定value的'枚举'
     *
     * @param clazz
     * @param value
     * @return SuperEnum
     * @throws
     */
    public static <T extends SuperEnum> T getEnumByValue(Class<T> clazz, int value) {
        for(T _enum : clazz.getEnumConstants())
            if(_enum.getValue().equals(value))
                return _enum;
        return null;
    }
    
    /**
     * 返回指定名称的'枚举'
     * @param name
     * @return SuperEnum
     * @throws
     */
    public static <T extends SuperEnum> T getEnumByName(Class<T> clazz, String name) {
        for(T _enum : clazz.getEnumConstants())
            if(_enum.getName().equals(name))
                return _enum;
        return null;
    }
}
