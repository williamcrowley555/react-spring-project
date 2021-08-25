package com.william.createwebservice.service;

import com.william.createwebservice.shared.dto.RoleDTO;
import com.william.createwebservice.ui.model.response.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    RoleDTO getRoleByName(Role roleName);
}
