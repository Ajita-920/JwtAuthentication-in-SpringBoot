package com.advancedjavabyajita.springsecurityjwt.dto;

import com.advancedjavabyajita.springsecurityjwt.entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDto {
  private String firstname;
  private String lastname;
  private String email;
  private String password;


  private Role role;

}
