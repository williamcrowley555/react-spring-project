package com.william.createwebservice.io.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tables")
public class TableEntity implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    @NotBlank
//    @Size(max = 30)
//    @Column(nullable = false, length = 30)
//    private String tableId;
    @OneToOne(mappedBy="tables")
    private BookingEntity bookings;

    @NotBlank
    @Size(max = 2)
    @Column(nullable = false, length = 2)
    private int seat;

    @NotBlank
    @Column(nullable = false)
    private boolean status;

    public TableEntity() {
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
        return "TableEntity{" +
                "id=" + id +
                ", seat=" + seat +
                ", status=" + status +
                '}';
    }
}