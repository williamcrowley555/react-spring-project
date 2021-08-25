package com.william.createwebservice.io.repository;

import com.william.createwebservice.io.entity.BookingEntity;
import com.william.createwebservice.io.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<BookingEntity,Long> {
    Optional<BookingEntity> findByBookingId(String bookingId);
}
