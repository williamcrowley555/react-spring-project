package com.william.createwebservice.io.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bookings")
public class BookingEntity implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 30)
    @Column(nullable = false, length = 30)
    private String bookingId;


    @OneToOne
    @JoinColumn(name = "table_id")
    private TableEntity tables;

    @NotBlank
    @Size(max = 2)
    @Column(nullable = false, length = 2)
    private int guestNumber;

    @NotBlank
    @Column(columnDefinition = "DATE")
    private LocalDate bookedDate;

    @NotBlank
    @Column(columnDefinition = "TIME")
    private LocalTime bookedTime;

    @NotBlank
    @Column(nullable = false)
    private boolean status;

    public BookingEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BookingEntity{" +
                "id=" + id +
                ", bookingId='" + bookingId + '\'' +
                ", guestNumber=" + guestNumber +
                ", bookedDate=" + bookedDate +
                ", bookedTime=" + bookedTime +
                ", status=" + status +
                '}';
    }
}

