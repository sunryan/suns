package com.ryan.suns.common.model.user;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lr
 * @date 2018/3/22.
 */
@Data
public class SysRole implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Integer roleId;
    private String roleName;
    private String roleCode;
    private String roleDesc;
    private Date createTime;
    private Date updateTime;
    private String delFlag;

}
