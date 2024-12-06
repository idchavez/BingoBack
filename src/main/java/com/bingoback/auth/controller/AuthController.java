package com.bingoback.auth.controller;

import com.bingoback.auth.dto.AuthCreateUserRequest;
import com.bingoback.auth.dto.AuthLoginRequest;
import com.bingoback.auth.dto.AuthResponse;
import com.bingoback.auth.persistence.entity.InvalidarToken;
import com.bingoback.auth.persistence.repository.InvalidarTokenRepository;
import com.bingoback.auth.service.UserDetailsServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/bingo/auth")
@CrossOrigin(value = "http://localhost:4200")
public class AuthController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private InvalidarTokenRepository invalidarTokenRepository;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest userRequest){
        return new ResponseEntity<>(this.userDetailsService.loginUser(userRequest), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid AuthCreateUserRequest authCreateUser){
        return new ResponseEntity<>(this.userDetailsService.createUser(authCreateUser), HttpStatus.CREATED);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        InvalidarToken invalidarToken = new InvalidarToken();
        invalidarToken.setToken(token);
        invalidarToken.setInvalidatedAt(LocalDateTime.now());
        invalidarTokenRepository.save(invalidarToken);

        return ResponseEntity.ok("Sesion cerrada con exito!");
    }
}
