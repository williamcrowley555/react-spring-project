package com.william.createwebservice.service;

import com.william.createwebservice.shared.dto.RoleDTO;
import com.william.createwebservice.shared.enums.Role;

public interface RoleService {
    RoleDTO getRoleByName(Role roleName);
}
