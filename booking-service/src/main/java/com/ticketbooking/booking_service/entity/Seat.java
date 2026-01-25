package com.ticketbooking.booking_service.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "seat")
@Data
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bus_id", nullable = false)
    private Long busId;

    @Column(name = "seat_number", nullable = false)
    private Integer seatNumber;
}
