package com.ticketbooking.booking_service.repository;

import com.ticketbooking.booking_service.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByBusIdAndStatus(Long busId, String status);

    @Query(
            value = "SELECT id FROM users WHERE username = :userName",
            nativeQuery = true
    )
    Optional<Long> getUserId(@Param("userName") String userName);

    @Query("""
        SELECT b FROM Booking b
        WHERE b.status = 'LOCKED'
        AND b.createdAt < :expiryTime
    """)
    List<Booking> findExpiredLocks(@Param("expiryTime") LocalDateTime expiryTime);
}