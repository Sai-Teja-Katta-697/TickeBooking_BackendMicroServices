package com.ticketbooking.booking_service.util;

import com.ticketbooking.booking_service.entity.Booking;
import com.ticketbooking.booking_service.repository.BookingRepository;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@EnableScheduling
public class SeatReleaseScheduler {

    private final BookingRepository bookingRepository;

    public SeatReleaseScheduler(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Scheduled(fixedDelay = 60000) // every 1 minute
    public void releaseExpiredSeats() {
        LocalDateTime expiry = LocalDateTime.now().minusMinutes(2);

        List<Booking> expiredBookings =
                bookingRepository.findExpiredLocks(expiry);

        expiredBookings.forEach(b -> b.setStatus("AVAILABLE"));

        bookingRepository.saveAll(expiredBookings);
    }
}
