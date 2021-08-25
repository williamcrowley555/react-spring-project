package com.william.createwebservice.ui.controller;

import com.william.createwebservice.service.BookingService;
import com.william.createwebservice.shared.dto.BookingDTO;
import com.william.createwebservice.shared.dto.UserDTO;
import com.william.createwebservice.ui.model.response.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping(path = "api/booking")
public class BookingContorller {
    @Autowired
    private BookingService bookingService;

    @GetMapping(path = "/{id}")
    public void getUser(@PathVariable Long id) {
        BookingDTO bookingDTO = bookingService.getById(id);
    }
}
