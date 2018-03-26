package com.ryan.suns.user.service;

import com.ryan.suns.api.user.MenuService;
import com.ryan.suns.common.model.user.SysMenu;
import com.ryan.suns.user.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lr
 * @date 2018/3/26
 */
@Service
public class MenuServiceImpl implements MenuService {
    
    @Autowired
    private MenuMapper menuMapper;
    
    @Override
    public List<SysMenu> findMenuByRoleCode(String roleCode) {
        return menuMapper.findMenuByRoleCode(roleCode);
    }
}
