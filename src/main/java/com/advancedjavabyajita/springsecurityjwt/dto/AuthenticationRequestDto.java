package com.advancedjavabyajita.springsecurityjwt.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthenticationRequestDto {

    private String email;
    private String password;
}
