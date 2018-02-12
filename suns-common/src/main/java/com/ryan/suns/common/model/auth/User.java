package com.ryan.suns.common.model.auth;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;


@Data
public class User implements Serializable{
    
    
    private Integer id;

    private String username;

    private String password;

    /**
     * 是否启用
     */
    private Integer enable;
    
    private List<Role> roleList;
    
}