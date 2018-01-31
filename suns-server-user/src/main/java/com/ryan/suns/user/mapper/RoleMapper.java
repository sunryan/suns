package com.ryan.suns.user.mapper;


import com.ryan.suns.common.model.auth.Role;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RoleMapper {
    public List<Role> queryRoleListWithSelected(Integer id);
}