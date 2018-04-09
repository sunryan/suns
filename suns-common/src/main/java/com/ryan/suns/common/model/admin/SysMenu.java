package com.ryan.suns.common.model.admin;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.ryan.suns.common.model.BaseEntity;
import com.ryan.suns.common.model.enums.DeleteEnum;
import com.ryan.suns.common.model.enums.MenuType;
import lombok.Data;

import java.util.List;

/**
 * @author lr
 * @date 2018/3/26
 */
@Data
@TableName("sys_menu")
public class SysMenu extends BaseEntity<SysMenu>{
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 菜单名称
     */
    private String name;
    
    /**
     * 菜单权限标识
     */
    private String permission;
    
    /**
     * 请求链接
     */
    private String url;
    
    /**
     * 请求方法
     */
    private String method;
    
    /**
     * 父菜单ID
     */
    private Integer parentId;
    
    /**
     * 图标
     */
    private String icon;
    
    /**
     * VUE页面
     */
    private String component;
    
    /**
     * 排序值
     */
    private Integer sort;
    
    /**
     * 菜单类型
     */
    private MenuType type;
    
    /**
     * 0--正常 1--删除
     */
    private DeleteEnum delFlag;
    
    @TableField(exist = false)
    private List<SysMenu> children;
    
}
