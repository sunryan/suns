package com.ryan.suns.common.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lr
 * @date 2018/3/27
 */
@Data
public abstract class BaseEntity<T extends Model> extends Model<T> {
    
    
    /**
     * 通用主键
     */
    private String id;
    
    
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField(update = "now()")
    private Date updateTime;
    
    @Override
    protected Serializable pkVal() {
        return id;
    }
    
}
