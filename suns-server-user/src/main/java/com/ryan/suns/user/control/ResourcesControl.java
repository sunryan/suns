package com.ryan.suns.user.control;

import com.ryan.suns.api.auth.ResourcesService;
import com.ryan.suns.api.feign.user.ResourcesClient;
import com.ryan.suns.common.model.auth.Resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by ryan on 2018/1/31.
 */
@RestController
@RequestMapping("/api/resources")
public class ResourcesControl implements ResourcesClient{


    @Autowired
    private ResourcesService resourcesService;

    @Override
    public List<Resources> loadShiroFilter() {
        return resourcesService.loadShiroFilter();
    }

    @Override
    public List<Resources> loadUserResources(Integer userId) {
        return resourcesService.loadUserResources(userId);
    }
}
