package com.ryan.suns.admin.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ryan.suns.admin.mapper.MenuMapper;
import com.ryan.suns.api.user.MenuService;
import com.ryan.suns.common.model.admin.SysMenu;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lr
 * @date 2018/3/26
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, SysMenu>  implements MenuService {
    
    @Override
    public List<SysMenu> findMenuByRoleCode(String roleCode) {
        return baseMapper.findMenuByRoleCode(roleCode);
    }
    
    @Override
    @Cacheable(value = "menu_tree", keyGenerator = "keyGenerator")
    public List<SysMenu> findMenuTree() {
        return baseMapper.findMenuTree();
    }
}
