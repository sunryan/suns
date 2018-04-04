package com.ryan.suns.common.model.admin;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.ryan.suns.common.model.BaseEntity;
import com.ryan.suns.common.model.enums.DeleteEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author lr
 * @date 2018/3/22.
 */
@Data
@TableName("sys_role")
public class SysRole extends BaseEntity<SysRole> implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 角色名称
     */
    private String roleName;
    
    /**
     * 角色编码
     */
    private String roleCode;
    
    /**
     * 删除标识（0-正常,1-删除）
     */
    private DeleteEnum delFlag;
    
    /**
     * 菜单列表
     */
    @TableField(exist = false)
    private List<SysMenu> menuList;
    
}
