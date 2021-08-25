package com.william.createwebservice.io.repository;

import com.william.createwebservice.io.entity.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TableRepository extends JpaRepository<TableEntity, Long> {
    @Override
    Optional<TableEntity> findById(Long id);

    Optional<TableEntity> findByStatus(Boolean status);

    Optional<TableEntity> findBySeat(int seat);

}
