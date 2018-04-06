package com.ryan.suns.common.model.enums.suport;

import com.baomidou.mybatisplus.enums.IEnum;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author lr
 * @date 2018/4/4
 */
@JsonSerialize(using = EnumSerializer.class)
@JsonDeserialize(using = EnumDeserializer.class)
public interface SuperEnum extends IEnum {
    
    /**
     * 获取value字段解释
     * @return
     */
    String getName();
    
    /**
     * 获取枚举名称
     * @return
     */
    String getEnumName();
    
    /**
     * 获取枚举className
     * @return
     */
    String getClassName();
    
    
    
    
}
