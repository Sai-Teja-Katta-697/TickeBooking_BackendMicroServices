package com.tickebooking.bus_service.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "bus_info")
@Data
public class BusInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "bus_number")
    private String busNumber;
    @Column(name = "start_time")
    private String startTime;
    @Column(name = "end_time")
    private String endTime;
    @Column(name = "travel_from")
    private String travelFrom;
    @Column(name = "travel_to")
    private String travelTo;
    @Column(name = "bus_type")
    private String busType;
    @Column(name = "fare")
    private double fare;
}
