package com.tickebooking.bus_service.controller;

import com.tickebooking.bus_service.dto.request.BusFilter;
import com.tickebooking.bus_service.dto.response.BusInfoResponse;
import com.tickebooking.bus_service.dto.response.PageResponseDto;
import com.tickebooking.bus_service.service.BusInfoService;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.tickebooking.bus_service.util.Constants.COMMON_ENDPOINT;

@RestController
@RequestMapping(COMMON_ENDPOINT + "bus-list")
@Data
public class BusInfoController {
    private final BusInfoService busInfoService;

    @GetMapping
    public PageResponseDto<BusInfoResponse> getBuses(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size,
                                                     BusFilter busFilter) {
        return busInfoService.getPagebleBusInfo(page, size,busFilter);
    }
}
