package com.william.createwebservice.service.impl;

import com.william.createwebservice.exception.RoleServiceException;
import com.william.createwebservice.io.entity.RoleEntity;
import com.william.createwebservice.io.repository.RoleRepository;
import com.william.createwebservice.service.RoleService;
import com.william.createwebservice.shared.dto.RoleDTO;
import com.william.createwebservice.shared.enums.Role;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Set<RoleEntity> convertToEntity(Set<String> strRoles) {
        ModelMapper modelMapper = new ModelMapper();
        Set<RoleEntity> roles = new HashSet<>();

        if (strRoles == null || strRoles.isEmpty()) {
            RoleDTO userRole = getRoleByName(Role.ROLE_USER);

            roles.add(modelMapper.map(userRole, RoleEntity.class));
        } else {
            strRoles.stream().forEach((role) -> {
                switch (role) {
                    case "admin":
                        RoleDTO adminRole = getRoleByName(Role.ROLE_ADMIN);
                        roles.add(modelMapper.map(adminRole, RoleEntity.class));

                        break;
                    case "mod":
                        RoleDTO modRole = getRoleByName(Role.ROLE_MODERATOR);
                        roles.add(modelMapper.map(modRole, RoleEntity.class));

                        break;
                    case "staff":
                        RoleDTO staffRole = getRoleByName(Role.ROLE_STAFF);
                        roles.add(modelMapper.map(staffRole, RoleEntity.class));

                        break;
                    default:
                        RoleDTO userRole = getRoleByName(Role.ROLE_USER);
                        roles.add(modelMapper.map(userRole, RoleEntity.class));
                }
            });
        }

        return roles;
    }

    @Override
    public RoleDTO getRoleByName(Role roleName) {
        Optional<RoleEntity> optional = roleRepository.findByName(roleName);
        if (optional.isEmpty()) {
            throw new RoleServiceException("Role is not found");
        }

        ModelMapper modelMapper = new ModelMapper();
        RoleEntity roleEntity = optional.get();
        RoleDTO returnValue = modelMapper.map(roleEntity, RoleDTO.class);

        return returnValue;
    }
}
