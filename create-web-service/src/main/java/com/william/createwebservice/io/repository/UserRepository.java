package com.william.createwebservice.io.repository;

import com.william.createwebservice.io.entity.RoleEntity;
import com.william.createwebservice.io.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByUserId(String userId);
    Collection<UserEntity> findByRolesIn(Collection<RoleEntity> roles);
    Boolean existsByEmail(String email);
}
