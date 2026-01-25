package com.tickebooking.bus_service.service;

import com.tickebooking.bus_service.dto.request.BusFilter;
import com.tickebooking.bus_service.dto.response.BusInfoResponse;
import com.tickebooking.bus_service.dto.response.PageResponseDto;
import com.tickebooking.bus_service.entity.BusInfo;
import com.tickebooking.bus_service.repository.BusInfoRepo;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class BusInfoService {
    private final BusInfoRepo busInfoRepo;

    public PageResponseDto<BusInfoResponse> getPagebleBusInfo(int page, int size, BusFilter filter) {
        Pageable pageable = PageRequest.of(page, size);
        Page<BusInfo> pageBusInfo = busInfoRepo.findByFromAndTo(filter.getFrom(),filter.getTo(),filter.getBusType(),pageable);
        List<BusInfoResponse> busInfoResponseList = pageBusInfo.getContent()
                .stream().map(
                        bus -> {
                            return BusInfoResponse.builder()
                                    .id(bus.getId())
                                    .busNumber(bus.getBusNumber())
                                    .busType(bus.getBusType())
                                    .to(bus.getTravelTo())
                                    .from(bus.getTravelFrom())
                                    .startTime(bus.getStartTime())
                                    .endTime(bus.getEndTime())
                                    .fare(bus.getFare())
                                    .build();
                        }).toList();
        return new PageResponseDto<>(
                busInfoResponseList,  pageBusInfo.getNumber(),
                pageBusInfo.getTotalPages(),
                pageBusInfo.getTotalElements(),
                pageBusInfo.getSize()
        );
    }

}
