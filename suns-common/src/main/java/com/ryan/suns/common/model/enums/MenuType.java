package com.ryan.suns.common.model.enums;

import com.ryan.suns.common.model.enums.suport.SuperEnum;

import java.io.Serializable;

/**
 * @author lr
 * @date 2018/4/9
 */
public enum MenuType implements SuperEnum {
    
    
    DIRECTORY(1, "目录"),
    
    MENU(2, "菜单"),
    
    BUTTON(3, "按钮"),
    
    API(4, "操作");
    
    private int value;
    
    private String name;
    
    
    MenuType(int value, String name){
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
