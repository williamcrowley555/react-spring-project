package com.william.createwebservice.io.repository;

import com.william.createwebservice.io.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<BookingEntity,Long> {
}
