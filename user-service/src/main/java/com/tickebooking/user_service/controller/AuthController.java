package com.tickebooking.user_service.controller;

import com.tickebooking.user_service.dto.LoginRequestDTO;
import com.tickebooking.user_service.dto.LoginResponseDTO;
import com.tickebooking.user_service.dto.UserRequestDTO;
import com.tickebooking.user_service.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.tickebooking.user_service.util.Constants.COMMON_ENDPOINT;

@RestController
@RequestMapping(COMMON_ENDPOINT + "auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRequestDTO request) {
        userService.register(request);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        LoginResponseDTO responseDTO = userService.login(request);
        return ResponseEntity.ok(responseDTO);
    }
}
