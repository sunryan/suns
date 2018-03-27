package com.ryan.suns.common.model.user;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.ryan.suns.common.model.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lr
 * @date 2018/3/22.
 */
@Data
@TableName("sys_user")
public class SysUser extends BaseEntity<SysRole> implements Serializable {
    
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Integer userId;
    
    /**
     * 用户名
     */
    private String username;
    
    /**
     * 密码
     */
    private String password;
    
    /**
     * 随机盐
     */
    private String salt;
    
    /**
     * 手机号
     */
    private String mobile;
    
    /**
     * 0-正常，1-删除
     */
    private String delFlag;
    
    /**
     * 简介
     */
    private String introduction;
    
    /**
     * 头像
     */
    private String avatar;

    /**
     * 部门ID
     */
    private Integer deptId;
    
    /**
     * 部门名称
     */
    @TableField(exist = false)
    private String deptName;

    /**
     * 角色列表
     */
    @TableField(exist = false)
    private List<SysRole> roleList = new ArrayList<>();
    
    @Override
    protected Serializable pkVal() {
        return userId;
    }
}

