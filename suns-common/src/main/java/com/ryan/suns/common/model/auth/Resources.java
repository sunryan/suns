package com.ryan.suns.common.model.auth;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Data
public class Resources implements Serializable{
    
    private static final long serialVersionUID = -6812242071705361506L;
    
    private Integer id;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源url
     */
    private String resurl;

    /**
     * 资源类型   1:菜单    2：按钮
     */
    private Integer type;

    /**
     * 父资源
     */
    private Integer parentId;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 是否选中
     */
    private String checked;
    
}