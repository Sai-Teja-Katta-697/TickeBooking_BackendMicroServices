package com.tickebooking.user_service.service;

import com.tickebooking.user_service.dto.LoginRequestDTO;
import com.tickebooking.user_service.dto.LoginResponseDTO;
import com.tickebooking.user_service.dto.UserRequestDTO;
import com.tickebooking.user_service.entity.User;
import com.tickebooking.user_service.repository.UserRepository;
import com.tickebooking.user_service.util.JwtUtil;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Data
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    public void register(UserRequestDTO request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user.setEnabled(true);
        userRepository.save(user);
    }
    public LoginResponseDTO login(LoginRequestDTO request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        if (!user.getEnabled()) {
            throw new RuntimeException("User is disabled");
        }

        return LoginResponseDTO.builder()
                .username(request.getUsername())
                .token(jwtUtil.generateToken(user.getUsername(), user.getRole()))
                .build();

    }
}
