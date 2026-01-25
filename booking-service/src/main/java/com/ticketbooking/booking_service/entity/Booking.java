package com.ticketbooking.booking_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(
    name = "booking",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"bus_id", "seat_number"})
    }
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bus_id", nullable = false)
    private Long busId;

    @Column(name = "booking_id", nullable = false)
    private String bookingId;

    @Column(name = "seat_number", nullable = false)
    private Integer seatNumber;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "status", nullable = false)
    private String status; // BOOKED / CANCELLED

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
