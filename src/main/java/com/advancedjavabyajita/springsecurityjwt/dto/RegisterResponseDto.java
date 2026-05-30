package com.advancedjavabyajita.springsecurityjwt.dto;

import com.advancedjavabyajita.springsecurityjwt.entity.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegisterResponseDto {
    private String firstname;
    private String lastname;
    private String email;
    private Role role;
}
