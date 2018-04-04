package com.ryan.suns.common.model.enums.suport;

import com.baomidou.mybatisplus.enums.IEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lr
 * @date 2018/4/4
 */
public interface SuperEnum extends IEnum {
    
    String getName();
    
    Map<Class, Map> ENUM_MAP = new HashMap<>();
    
    static <E extends Enum & SuperEnum> E getByName(String name, Class<E> clazz) throws NoMatchedEnumException {
        Map enumMap = ENUM_MAP.get(clazz);
        
        if(null == enumMap) {
            E[] enums = clazz.getEnumConstants();
            
            enumMap = new HashMap<String, E>();
            
            for(E current : enums) {
                enumMap.put(current.getName(), current);
            }
        }
        
        E result =  (E) enumMap.get(name);
        if(result != null) {
            return result;
        } else {
            throw new NoMatchedEnumException("No element matches " + name);
        }
    }
}
