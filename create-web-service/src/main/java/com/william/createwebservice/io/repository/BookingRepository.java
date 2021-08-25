package com.william.createwebservice.io.repository;

import com.william.createwebservice.io.entity.BookingEntity;
import com.william.createwebservice.io.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity,Long> {
    @Override
    Optional<BookingEntity> findById(Long id);
}
