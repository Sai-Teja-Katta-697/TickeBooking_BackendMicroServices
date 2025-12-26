package com.tickebooking.user_service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserRequestDTO {
    private String username;
    private String email;
    private String password;
    private String role;
    private Boolean enabled = true;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
