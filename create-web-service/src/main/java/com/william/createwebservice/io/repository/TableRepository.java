package com.william.createwebservice.io.repository;

import com.william.createwebservice.io.entity.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<TableEntity, Long> {
}
