package com.william.createwebservice.service.impl;

import com.william.createwebservice.exception.BookingServiceException;
import com.william.createwebservice.io.entity.BookingEntity;
import com.william.createwebservice.io.entity.RoleEntity;
import com.william.createwebservice.io.repository.BookingRepository;
import com.william.createwebservice.service.BookingService;
import com.william.createwebservice.shared.dto.BookingDTO;
import com.william.createwebservice.shared.dto.RoleDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public BookingDTO getById(Long id) {
        Optional<BookingEntity> optional = bookingRepository.findById(id);
        BookingEntity bookingEntity = optional.get();
        System.out.println(bookingEntity);
        return null;
    }

    @Override
    public BookingDTO getBookingID(String id_booking) {
        Optional<BookingEntity> optional = bookingRepository.findByBookingId(id_booking);
        if (optional.isEmpty()) {
            throw new BookingServiceException("Id booking is not found");
        }
        ModelMapper modelMapper = new ModelMapper();
        BookingEntity bookingEntity = optional.get();
        BookingDTO returnValue = modelMapper.map(bookingEntity, BookingDTO.class);
        System.out.println(bookingEntity);
        return returnValue;
    }


}
