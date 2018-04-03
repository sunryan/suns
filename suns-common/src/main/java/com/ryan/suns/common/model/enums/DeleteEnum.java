package com.ryan.suns.common.model.enums;

import com.baomidou.mybatisplus.enums.IEnum;

import java.io.Serializable;

/**
 * @author lr
 * @date 2018/4/3
 */
public enum DeleteEnum implements IEnum {
    
    NORMAL(0, "正常"),
    
    DELETE(1, "已删除");
    
    private int value;
    
    private String name;
    
    private DeleteEnum(int value, String name){
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
