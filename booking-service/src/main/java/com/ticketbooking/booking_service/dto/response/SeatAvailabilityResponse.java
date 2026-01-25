package com.ticketbooking.booking_service.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class SeatAvailabilityResponse {
    private Long busId;
    private List<SeatStatusDto> seats;
}
