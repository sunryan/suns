package com.ryan.suns.user.mapper;


import com.ryan.suns.common.model.auth.Resources;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ResourcesMapper {
    
    List<Resources> queryAll();
    
    List<Resources> loadUserResources(@Param("userId") Integer userId);
}