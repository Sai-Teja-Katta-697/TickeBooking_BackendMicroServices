package com.ticketbooking.booking_service.kafka;

import com.ticketbooking.avro.BookingEvent;
import com.ticketbooking.booking_service.service.SeatAvailabilityService;
import lombok.Data;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Data
public class BookingEventConsumer {
    private final SeatAvailabilityService service;
    @KafkaListener(
        topics = "booking-avro",
        groupId = "booking-service-group"
    )
    public void consume(BookingEvent event) {

        service.bookSeats(event);
        System.out.println("Received booking event: " + event);
        // use event.getBusId(), event.getSeatNumber(), event.getStatus()
    }
}
