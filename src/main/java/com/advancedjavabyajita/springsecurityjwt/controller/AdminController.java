package com.advancedjavabyajita.springsecurityjwt.controller;

import com.advancedjavabyajita.springsecurityjwt.dto.UserResponseDto;
import com.advancedjavabyajita.springsecurityjwt.entity.User;
import com.advancedjavabyajita.springsecurityjwt.repository.UserRepository;
import com.advancedjavabyajita.springsecurityjwt.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {


    private final UserService userService;
    private final UserRepository userRepository;

    public AdminController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/hello")
    public String admin(){
        return "Hello admin";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAllUsers/")
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        List<UserResponseDto> user = userService.getAllUsers();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Integer id) {
        User user = userService.getUserById(id);

        UserResponseDto dto = UserResponseDto.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();

        return ResponseEntity.ok(dto);
    }

    @PostMapping("/post")
    public String post(){
        return"POST: admin controller";
    }

    @PutMapping("/put")
    public String put(){
        return"PUT: admin controller";
    }

    @PostMapping("/delete")
    public String delete(){
        return"DELETE: admin controller";
    }


}
