package com.william.createwebservice.shared.dto;

import com.william.createwebservice.io.entity.BookingEntity;

public class TableDTO {
    private Long id;
    private int seat;
    private boolean status;

    public TableDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TableDTO{" +
                "id=" + id +
                ", seat=" + seat +
                ", status=" + status +
                '}';
    }
}
