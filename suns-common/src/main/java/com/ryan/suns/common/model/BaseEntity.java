package com.ryan.suns.common.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

import java.util.Date;

/**
 * @author lr
 * @date 2018/3/27
 */
@Data
public abstract class BaseEntity<T extends Model> extends Model<T> {
    
    
    /**
     * 创建时间
     */
    
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField(update = "now()")
    private Date updateTime;
    
}
