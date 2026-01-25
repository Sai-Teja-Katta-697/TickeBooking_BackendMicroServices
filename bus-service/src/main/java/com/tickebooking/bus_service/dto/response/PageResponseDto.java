package com.tickebooking.bus_service.dto.response;

import lombok.Data;

import java.util.List;
@Data
public class PageResponseDto<T> {

    private List<T> data;
    private int currentPage;
    private int totalPages;
    private long totalElements;
    private int pageSize;

    public PageResponseDto(List<T> data, int currentPage, int totalPages,
                           long totalElements, int pageSize) {
        this.data = data;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.pageSize = pageSize;
    }

    // getters & setters
}
