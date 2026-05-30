package com.advancedjavabyajita.springsecurityjwt.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String role;
}
