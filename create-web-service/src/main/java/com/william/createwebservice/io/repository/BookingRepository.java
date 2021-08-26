package com.william.createwebservice.io.repository;

import com.william.createwebservice.io.entity.BookingEntity;
import com.william.createwebservice.io.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity,Long> {
    @Override
    Optional<BookingEntity> findById(Long id);

    Optional<BookingEntity> findByBookingId(String id);

    List<BookingEntity> findByBookedDate(LocalDate bookedDate);

}
