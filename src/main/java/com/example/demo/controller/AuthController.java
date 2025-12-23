package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(
            @RequestBody Map<String, String> request) {

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Login successful");
        response.put("email", request.get("email"));
        response.put("role", "USER");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(
            @RequestBody Map<String, String> request) {

        Map<String, Object> response = new HashMap<>();
        response.put("message", "User registered");
        response.put("email", request.get("email"));
        response.put("role", "USER");

        return ResponseEntity.ok(response);
    }
}
