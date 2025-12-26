package com.tickebooking.user_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.tickebooking.user_service.util.Constants.COMMON_ENDPOINT;

@RestController
@RequestMapping(COMMON_ENDPOINT+"user")
public class UserController {
    @GetMapping("/test")
    public String test() {
        return "User service internal path works";
    }
}
