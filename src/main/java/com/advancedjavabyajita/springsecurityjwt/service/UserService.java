package com.advancedjavabyajita.springsecurityjwt.service;

import com.advancedjavabyajita.springsecurityjwt.dto.AuthenticationResponseDto;
import com.advancedjavabyajita.springsecurityjwt.dto.UserResponseDto;
import com.advancedjavabyajita.springsecurityjwt.entity.User;
import com.advancedjavabyajita.springsecurityjwt.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;


@Service
public class UserService {

    private final UserRepository  userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }

    public List<UserResponseDto> getAllUsers() {
       return userRepository.findAll().stream()
               .map(user-> new UserResponseDto(
                       user.getId(),
                       user.getFirstname(),
                       user.getLastname(),
                       user.getEmail(),
                       user.getRole().name()))
               .toList();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(()-> new RuntimeException("User with " + id + " not found"));
    }

    public UserResponseDto getMyProfile(User user){
      return new UserResponseDto(
              user.getId(),
              user.getFirstname(),
              user.getLastname(),
              user.getEmail(),
              user.getRole().name());
    }
}
