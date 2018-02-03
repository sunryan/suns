package com.ryan.suns.common.model.auth;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@ToString
public class User implements Serializable{
    private static final long serialVersionUID = -8736616045315083846L;
    
    private Integer id;

    private String username;

    private String password;

    /**
     * 是否启用
     */
    private Integer enable;
    
    private List<Role> roleList;
    private Object authorities;

    public Object getAuthorities() {
        return authorities;
    }
}