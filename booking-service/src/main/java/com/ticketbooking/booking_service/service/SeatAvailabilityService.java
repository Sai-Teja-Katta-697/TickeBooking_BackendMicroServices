package com.ticketbooking.booking_service.service;

import com.ticketbooking.avro.BookingEvent;
import com.ticketbooking.booking_service.dto.request.BookSeatsRequestDTO;
import com.ticketbooking.booking_service.dto.response.SeatAvailabilityResponse;
import com.ticketbooking.booking_service.dto.response.SeatStatusDto;
import com.ticketbooking.booking_service.entity.Booking;
import com.ticketbooking.booking_service.repository.BookingRepository;
import com.ticketbooking.booking_service.repository.SeatRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class SeatAvailabilityService {
    private final SeatRepository seatRepo;
    private final BookingRepository bookingRepo;

    public SeatAvailabilityResponse getSeatAvailability(Long busId) {
        List<Integer> bookedSeats = bookingRepo
                .findByBusIdAndStatus(busId, "BOOKED")
                .stream()
                .map(Booking::getSeatNumber)
                .toList();
        List<SeatStatusDto> seats = seatRepo.findByBusId(busId).stream().map(
                seat -> {
                    SeatStatusDto dto = new SeatStatusDto();
                    dto.setSeatNumber(seat.getSeatNumber());
                    dto.setStatus(bookedSeats.contains(seat.getSeatNumber()) ? "Booked" : "AVAILABLE");
                    return dto;
                }
        ).toList();
        SeatAvailabilityResponse response = new SeatAvailabilityResponse();
        response.setSeats(seats);
        response.setBusId(busId);
        return response;
    }

    public void bookSeats(BookingEvent bookingEvent) {
        Long userId = bookingRepo.getUserId(bookingEvent.getUserId()).orElseThrow(RuntimeException::new);

        List<Booking> bookings = bookingEvent.getSeatNumber().stream()
                .map(seatNo -> Booking.builder()
                        .bookingId(bookingEvent.getBookingId())
                        .busId(bookingEvent.getBusId())
                        .seatNumber(seatNo)
                        .userId(userId)
                        .status(bookingEvent.getStatus())
                        .build()
                )
                .toList();
        bookingRepo.saveAll(bookings);
    }
}
