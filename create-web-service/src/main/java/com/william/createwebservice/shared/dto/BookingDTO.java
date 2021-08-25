package com.william.createwebservice.shared.dto;

import com.william.createwebservice.io.entity.TableEntity;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingDTO {
    private Long id;
    private LocalDate bookedDate;
    private LocalTime bookedTime;
    private String bookingId;
    private int guestNumber;
    private boolean status;
    private TableEntity tables;

    public BookingDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getBookedDate() {
        return bookedDate;
    }

    public void setBookedDate(LocalDate bookedDate) {
        this.bookedDate = bookedDate;
    }

    public LocalTime getBookedTime() {
        return bookedTime;
    }

    public void setBookedTime(LocalTime bookedTime) {
        this.bookedTime = bookedTime;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public int getGuestNumber() {
        return guestNumber;
    }

    public void setGuestNumber(int guestNumber) {
        this.guestNumber = guestNumber;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public TableEntity getTables() {
        return tables;
    }

    public void setTables(TableEntity tables) {
        this.tables = tables;
    }

    @Override
    public String toString() {
        return "BookingDTO{" +
                "id=" + id +
                ", bookedDate=" + bookedDate +
                ", bookedTime=" + bookedTime +
                ", bookingId='" + bookingId + '\'' +
                ", guestNumber=" + guestNumber +
                ", status=" + status +
                ", tables=" + tables +
                '}';
    }
}
