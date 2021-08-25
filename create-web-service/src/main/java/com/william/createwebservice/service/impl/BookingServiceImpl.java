package com.william.createwebservice.service.impl;

import com.william.createwebservice.io.repository.BookingRepository;
import com.william.createwebservice.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;

public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;
}
