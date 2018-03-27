package com.ryan.suns.common.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lr
 * @date 2018/3/27
 */
@Data
public class BaseEntity implements Serializable {
    
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    
}
