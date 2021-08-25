package com.william.createwebservice.service;

import com.william.createwebservice.shared.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {
    List<UserDTO> getUsers(int page, int limit);
    List<UserDTO> getUsersByRoleName(String roleName);
    UserDTO getUser(String email);
    UserDTO getUserByUserId(String userId);
    UserDTO createUser(UserDTO user);
    UserDTO updateUser(String userId, UserDTO user);
    void deleteUser(String userId);
    Boolean existsByEmail(String email);
}
