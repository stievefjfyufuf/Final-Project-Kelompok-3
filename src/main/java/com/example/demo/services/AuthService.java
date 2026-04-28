package com.example.demo.services;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Map<String, Object> register(RegisterRequest request) {
        Map<String, Object> response = new HashMap<>();

        if (userRepository.existsByEmail(request.getEmail())) {
            response.put("success", false);
            response.put("message", "Email sudah terdaftar");
            return response;
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        userRepository.save(user);

        response.put("success", true);
        response.put("message", "Register berhasil");
        return response;
    }

    public Map<String, Object> login(LoginRequest request) {
        Map<String, Object> response = new HashMap<>();

        User user = userRepository.findByEmail(request.getEmail());

        if (user == null) {
            response.put("success", false);
            response.put("message", "Email tidak ditemukan");
            return response;
        }

        if (!user.getPassword().equals(request.getPassword())) {
            response.put("success", false);
            response.put("message", "Password salah");
            return response;
        }

        response.put("success", true);
        response.put("message", "Login berhasil");
        response.put("userId", user.getId());
        response.put("name", user.getName());
        response.put("email", user.getEmail());

        return response;
    }
}