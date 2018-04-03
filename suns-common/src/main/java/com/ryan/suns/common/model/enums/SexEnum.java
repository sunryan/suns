package com.ryan.suns.common.model.enums;

import com.baomidou.mybatisplus.enums.IEnum;

import java.io.Serializable;

/**
 * @author lr
 * @date 2018/4/3
 */
public enum SexEnum implements IEnum {

    MAN(1, "男"),
    
    WOMAN(0, "女");
    
    private int value;
    
    private String name;
    
    private SexEnum(int value, String name){
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
}
