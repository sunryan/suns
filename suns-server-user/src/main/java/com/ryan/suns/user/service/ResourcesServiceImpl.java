package com.ryan.suns.user.service;

import cn.hutool.core.util.StrUtil;
import com.ryan.suns.api.auth.ResourcesService;
import com.ryan.suns.common.model.auth.Resources;
import com.ryan.suns.user.mapper.ResourcesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lr
 * @date 2018/1/23
 */
@Service
public class ResourcesServiceImpl implements ResourcesService {
    
    @Autowired
    private ResourcesMapper resourcesMapper;
    
    
    @Override
    public List<Resources> loadShiroFilter() {
        return queryAll();
    }
    
    @Override
    public List<Resources> loadUserResources(Integer userId) {
        return resourcesMapper.loadUserResources(userId);
    }
    
    @Override
    public List<Resources> queryAll() {
        return resourcesMapper.queryAll();
    }
}
