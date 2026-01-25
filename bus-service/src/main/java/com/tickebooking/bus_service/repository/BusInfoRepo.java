package com.tickebooking.bus_service.repository;

import com.tickebooking.bus_service.entity.BusInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BusInfoRepo extends JpaRepository<BusInfo, Long> {

    @Query("""
                SELECT b FROM BusInfo b
                WHERE (:destination_from IS NULL OR b.travelFrom = :destination_from)
                  AND (:destination_to IS NULL OR b.travelTo = :destination_to)
                  AND(:bus_type is NULL OR b.busType= :bus_type)
            """)
    Page<BusInfo> findByFromAndTo(
            @Param("destination_from") String from,
            @Param("destination_to") String to,
            @Param("bus_type") String busType,
            Pageable pageable
    );
}
