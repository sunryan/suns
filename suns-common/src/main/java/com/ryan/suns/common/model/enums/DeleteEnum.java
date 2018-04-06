package com.ryan.suns.common.model.enums;

import com.ryan.suns.common.model.enums.suport.SuperEnum;

import java.io.Serializable;

/**
 * @author lr
 * @date 2018/4/3
 */
public enum DeleteEnum implements SuperEnum {
    
    NORMAL(0, "正常"),
    
    DELETE(1, "已删除");
    
    private int value;
    
    private String name;
    
    DeleteEnum(int value, String name){
        this.value = value;
        this.name = name;
    }
    
    @Override
    public Serializable getValue() {
        return this.value;
    }
    
    public String getName() {
        return name;
    }
    
    public String getEnumName() {
        return name();
    }
    
    public String getClassName() {
        return getClass().getName();
    }
    
}
