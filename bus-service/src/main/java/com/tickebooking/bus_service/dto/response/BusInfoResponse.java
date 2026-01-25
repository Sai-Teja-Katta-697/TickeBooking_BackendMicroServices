package com.tickebooking.bus_service.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BusInfoResponse {

    private Long id;
    private String busNumber;
    private String startTime;
    private String endTime;
    private String from;
    private String to;
    private String busType;
    private double fare;
}
