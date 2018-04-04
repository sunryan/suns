package com.ryan.suns.common.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ryan.suns.common.model.enums.suport.SuperEnum;

import java.io.Serializable;

/**
 * @author lr
 * @date 2018/4/3
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SexEnum implements SuperEnum {

    MAN(1, "男", "MAN"),
    
    WOMAN(0, "女", "WOMAN");
    
    private int value;
    
    private String name;
    
    private String enumName;
    
    private SexEnum(int value, String name, String enumName){
        this.value = value;
        this.name = name;
        this.enumName = enumName;
    }
    
    @Override
    public Serializable getValue() {
        return this.value;
    }
    
    public String getName() {
        return name;
    }
    
    public String getEnumName() {
        return enumName;
    }
}
