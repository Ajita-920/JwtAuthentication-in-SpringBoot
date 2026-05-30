package com.advancedjavabyajita.springsecurityjwt.service;

import com.advancedjavabyajita.springsecurityjwt.dto.AuthenticationRequestDto;
import com.advancedjavabyajita.springsecurityjwt.dto.AuthenticationResponseDto;
import com.advancedjavabyajita.springsecurityjwt.dto.RegisterRequestDto;
import com.advancedjavabyajita.springsecurityjwt.dto.RegisterResponseDto;
import com.advancedjavabyajita.springsecurityjwt.entity.Role;
import com.advancedjavabyajita.springsecurityjwt.entity.User;
import com.advancedjavabyajita.springsecurityjwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public RegisterResponseDto register(RegisterRequestDto request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists: " + request.getEmail());
        }

        var user= User.builder()
              .firstname(request.getFirstname())
              .lastname(request.getLastname())
              .email(request.getEmail())
              .password(passwordEncoder.encode(request.getPassword()))
              .role(request.getRole())
                      .build();

     userRepository.save(user);

           return RegisterResponseDto.builder()
              .email(user.getEmail())
              .firstname(user.getFirstname())
              .lastname(user.getLastname())
              .role(user.getRole())
              .build();
            }

//authenticating user
    public AuthenticationResponseDto authenticate(AuthenticationRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", user.getRole());
        extraClaims.put("userId", user.getId());

        var jwtToken = jwtService.generateToken(extraClaims,user);
        return AuthenticationResponseDto.builder()
                .email(user.getEmail())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .role(user.getRole())
                .token(jwtToken)
                .build();
    }

}

