package com.ryan.suns.common.model.auth;

import lombok.Data;

import java.io.Serializable;

@Data
public class Role implements Serializable{
    
    private static final long serialVersionUID = -6140090613812307452L;
    
    private Integer id;

    private String roledesc;
    
    private Integer selected;
}