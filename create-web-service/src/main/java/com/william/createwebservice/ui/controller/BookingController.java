package com.william.createwebservice.ui.controller;

import com.william.createwebservice.service.BookingService;
import com.william.createwebservice.shared.dto.BookingDTO;
import com.william.createwebservice.shared.dto.UserDTO;
import com.william.createwebservice.ui.model.response.BookingResponse;
import com.william.createwebservice.ui.model.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(path = "api/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @Operation(summary = "Get a booking by its id booking")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the booking",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Booking not found",
                    content = @Content) })
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getBookingId(@PathVariable String id) {
        ModelMapper modelMapper = new ModelMapper();
        BookingDTO bookingDTO = bookingService.getBookingByBookingId(id);
        BookingResponse returnValue = modelMapper.map(bookingDTO, BookingResponse.class);
        return ResponseEntity.ok(returnValue);
    }


    @GetMapping(path = "/date")
    public void getBookingd() {
        LocalDate localDate = LocalDate.of(2020,04,03);
        bookingService.getListBookedDate(localDate);
    }
}