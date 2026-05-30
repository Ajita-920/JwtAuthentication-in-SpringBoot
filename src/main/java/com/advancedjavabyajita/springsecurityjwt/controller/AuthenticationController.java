package com.advancedjavabyajita.springsecurityjwt.controller;

import com.advancedjavabyajita.springsecurityjwt.dto.AuthenticationRequestDto;
import com.advancedjavabyajita.springsecurityjwt.dto.AuthenticationResponseDto;
import com.advancedjavabyajita.springsecurityjwt.dto.RegisterRequestDto;
import com.advancedjavabyajita.springsecurityjwt.dto.RegisterResponseDto;
import com.advancedjavabyajita.springsecurityjwt.service.AuthenticationService;
import com.advancedjavabyajita.springsecurityjwt.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("api/v1/auth")
public class AuthenticationController {


    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterRequestDto request){
            RegisterResponseDto registerUser = authenticationService.register(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(registerUser);
        }


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> login(@RequestBody AuthenticationRequestDto request) {
        AuthenticationResponseDto authResponse = authenticationService.authenticate(request);
        return ResponseEntity.ok(authResponse);
    }


}
