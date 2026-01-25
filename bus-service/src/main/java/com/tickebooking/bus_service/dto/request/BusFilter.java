package com.tickebooking.bus_service.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusFilter {
    private String from;
    private String to;
    private String busType;
}
