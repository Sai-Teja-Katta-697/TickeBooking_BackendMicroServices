package com.ticketbooking.booking_service.dto.response;

import lombok.Data;

@Data
public class SeatStatusDto {
    private Integer seatNumber;
    private String status;
}
