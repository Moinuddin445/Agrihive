package com.DigiMarket.AgriHive.controller;

import com.DigiMarket.AgriHive.DTO.LoginRequest;
import com.DigiMarket.AgriHive.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String result = userService.loginUser(loginRequest.getName(), loginRequest.getPhone(), loginRequest.getPassword());
        return ResponseEntity.ok(result);
    }
}

