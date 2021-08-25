package com.william.createwebservice.service;

import com.william.createwebservice.shared.dto.BookingDTO;
import com.william.createwebservice.ui.model.response.Role;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface BookingService {
    BookingDTO getBookingById(Long id);
    BookingDTO getBookingByBookingId(String booking_id);

}
