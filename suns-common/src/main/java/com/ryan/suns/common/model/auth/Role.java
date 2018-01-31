package com.ryan.suns.common.model.auth;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Role implements Serializable{
    
    private static final long serialVersionUID = -6140090613812307452L;
    
    private Integer id;

    private String roledesc;
    
    private Integer selected;
}