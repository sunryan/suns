package com.ryan.suns.common.model.user;

import com.ryan.suns.common.model.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lr
 * @date 2018/3/26
 */
@Data
public class SysMenu extends BaseEntity implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 菜单ID
     */
    private Integer menuId;
    
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
     * 菜单类型 （0菜单 1按钮）
     */
    private String type;
    
    /**
     * 0--正常 1--删除
     */
    private String delFlag;
}
