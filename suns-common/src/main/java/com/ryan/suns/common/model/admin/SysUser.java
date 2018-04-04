package com.ryan.suns.common.model.admin;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.ryan.suns.common.model.BaseEntity;
import com.ryan.suns.common.model.enums.DeleteEnum;
import com.ryan.suns.common.model.enums.SexEnum;
import lombok.Data;
import org.springframework.util.CollectionUtils;

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
     * 平台类型
     */
    private Integer platformType;
    
    /**
     * 性别 1：男 0：女
     */
    private SexEnum sex;
    
    /**
     * 手机号
     */
    private String mobile;
    
    /**
     * 邮箱
     */
    private String mail;
    
    
    /**
     * 0-正常，1-删除
     */
    private DeleteEnum delFlag;
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
    
    
    public List<String> getRoles(){
        List<String> list = new ArrayList<>();
        if(!CollectionUtils.isEmpty(roleList)){
            for(SysRole role : roleList){
                list.add(role.getRoleCode());
            }
        }
        return list;
    }
    
    public List<String> getPermissions(){
        List<String> list = new ArrayList<>();
        if(!CollectionUtils.isEmpty(roleList)){
            for(SysRole role : roleList){
                if(!CollectionUtils.isEmpty(role.getMenuList())) {
                    for (SysMenu menu : role.getMenuList()) {
                        list.add(menu.getPermission());
                    }
                }
            }
        }
        return list;
    }
}

