package com.bingoback.auth.controller;

import com.bingoback.auth.dto.AuthCreateUserRequest;
import com.bingoback.auth.dto.AuthLoginRequest;
import com.bingoback.auth.dto.AuthResponse;
import com.bingoback.auth.service.UserDetailsServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bingo/auth")
@CrossOrigin(value = "http://localhost:4200")
public class AuthController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest userRequest){
        return new ResponseEntity<>(this.userDetailsService.loginUser(userRequest), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid AuthCreateUserRequest authCreateUser){
        return new ResponseEntity<>(this.userDetailsService.createUser(authCreateUser), HttpStatus.CREATED);
    }
}
