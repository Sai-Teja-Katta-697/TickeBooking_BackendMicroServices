package com.ticketbooking.booking_service.repository;

import com.ticketbooking.booking_service.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByBusId(Long busId);
}