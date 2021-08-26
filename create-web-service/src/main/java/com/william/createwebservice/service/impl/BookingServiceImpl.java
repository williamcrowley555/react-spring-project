package com.william.createwebservice.service.impl;

import com.william.createwebservice.exception.BookingServiceException;
import com.william.createwebservice.io.entity.BookingEntity;
import com.william.createwebservice.io.entity.RoleEntity;
import com.william.createwebservice.io.entity.TableEntity;
import com.william.createwebservice.io.repository.BookingRepository;
import com.william.createwebservice.io.repository.TableRepository;
import com.william.createwebservice.service.BookingService;
import com.william.createwebservice.shared.dto.BookingDTO;
import com.william.createwebservice.shared.dto.RoleDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;


    @Override
    public BookingDTO getBookingById(Long id) {
        Optional<BookingEntity> optional = bookingRepository.findById(id);
        if (optional.isEmpty()) {
            throw new BookingServiceException("Id is not found");
        }
        ModelMapper modelMapper = new ModelMapper();
        BookingEntity bookingEntity = optional.get();
        BookingDTO returnValue = modelMapper.map(bookingEntity, BookingDTO.class);
        return returnValue;
    }

    @Override
    public BookingDTO getBookingByBookingId(String id_booking) {
        Optional<BookingEntity> optional = bookingRepository.findByBookingId(id_booking);
        if (optional.isEmpty()) {
            throw new BookingServiceException("Id booking is not found");
        }
        ModelMapper modelMapper = new ModelMapper();
        BookingEntity bookingEntity = optional.get();
        BookingDTO returnValue = modelMapper.map(bookingEntity, BookingDTO.class);
        return returnValue;
    }

    @Override
    public List<BookingDTO> getListBookedDate(LocalDate localDate) {
        List<BookingEntity> optional = bookingRepository.findByBookedDate(localDate);
        ModelMapper modelMapper = new ModelMapper();
        List<BookingDTO> list = new ArrayList<>();
        for (BookingEntity entity :optional) {
            BookingDTO returnValue = modelMapper.map(entity, BookingDTO.class);
            list.add(returnValue);
            System.out.println("/////////list//////////");
            System.out.println(list);
        }
        return list;
    }


}
