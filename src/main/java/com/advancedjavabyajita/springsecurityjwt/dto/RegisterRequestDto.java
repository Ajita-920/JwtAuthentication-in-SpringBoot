package com.advancedjavabyajita.springsecurityjwt.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegisterRequestDto {
  private String firstname;
  private String lastname;
  private String email;
  private String password;

}
