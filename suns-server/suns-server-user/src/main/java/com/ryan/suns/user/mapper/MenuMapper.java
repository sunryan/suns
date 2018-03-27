package com.ryan.suns.user.mapper;

import com.ryan.suns.common.model.user.SysMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author lr
 * @date 2018/3/26
 */
@Component
public interface MenuMapper extends SuperMapper<SysMenu>{
    
    List<SysMenu> findMenuByRoleCode(@Param("roleCode") String roleCode);
}
