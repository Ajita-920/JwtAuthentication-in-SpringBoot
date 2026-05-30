package com.advancedjavabyajita.springsecurityjwt.controller;

import com.advancedjavabyajita.springsecurityjwt.dto.AuthenticationResponseDto;
import com.advancedjavabyajita.springsecurityjwt.dto.UserResponseDto;
import com.advancedjavabyajita.springsecurityjwt.entity.User;
import com.advancedjavabyajita.springsecurityjwt.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
               this.userService = userService;
          }

    @GetMapping("/hello")
    public String userHello(Authentication auth){
        System.out.println(auth);
        return "Hello user";
    }

    @GetMapping("/myProfile")
    @PreAuthorize("hasAuthority('USER_READ')")
    public UserResponseDto getMyProfile(@AuthenticationPrincipal User user) {
            return  userService.getMyProfile(user);
        }


    @GetMapping("/getUser")
    @PreAuthorize("hasAuthority('USER_READ')")
    public ResponseEntity<UserResponseDto> getUserByEmail(@RequestParam String email) {
        User user = userService.getUserByEmail(email);

        UserResponseDto dto = UserResponseDto.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();

        return ResponseEntity.ok(dto);
    }

}
