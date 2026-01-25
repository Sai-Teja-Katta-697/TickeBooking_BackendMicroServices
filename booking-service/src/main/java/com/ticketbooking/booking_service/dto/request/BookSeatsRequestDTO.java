package com.ticketbooking.booking_service.dto.request;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class BookSeatsRequestDTO {
    private Long busId;
    private List<Integer> seatNumber;
    private String userId;
    private String status; // BOOKED / CANCELLED
}
