package com.ticketbooking.booking_service.controller;

import com.ticketbooking.avro.BookingEvent;
import com.ticketbooking.booking_service.dto.request.BookSeatsRequestDTO;
import com.ticketbooking.booking_service.dto.response.ApiResponse;
import com.ticketbooking.booking_service.dto.response.SeatAvailabilityResponse;
import com.ticketbooking.booking_service.kafka.BookingEventProducer;
import com.ticketbooking.booking_service.service.SeatAvailabilityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.ticketbooking.booking_service.util.Constants.COMMON_ENDPOINT;

@RestController
@RequestMapping(COMMON_ENDPOINT)
public class SeatAvailabilityController {
    private final SeatAvailabilityService service;
    private final BookingEventProducer bookingEventProducer;

    public SeatAvailabilityController(SeatAvailabilityService service, BookingEventProducer bookingEventProducer) {
        this.service = service;
        this.bookingEventProducer=bookingEventProducer;
    }

    @GetMapping("/{busId}/seats")
    public ResponseEntity<SeatAvailabilityResponse> getSeats(@PathVariable Long busId) {
        SeatAvailabilityResponse response = service.getSeatAvailability(busId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/book-seat")
    public ResponseEntity<ApiResponse> bookSeats(
            @RequestBody BookSeatsRequestDTO request) {
        BookingEvent eve=new BookingEvent();
        String id= UUID.randomUUID().toString();
        eve.setSeatNumber(request.getSeatNumber());
        eve.setStatus(request.getStatus());
        eve.setUserId(request.getUserId());
        eve.setBusId(request.getBusId());
        eve.setBookingId(id);
        bookingEventProducer.publish(id,eve);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse("SUCCESS", "Seats booked successfully"));
    }

    @GetMapping("/produce")
    public ResponseEntity<String> getProduce() {
        BookingEvent eve=new BookingEvent();
        eve.setSeatNumber(List.of(1,2,3));
        eve.setStatus("Hai");
        eve.setUserId("USer");
        eve.setBusId(1);
        bookingEventProducer.publish("2",eve);
        return ResponseEntity.ok("Ok");
    }
}
