package com.advancedjavabyajita.springsecurityjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.advancedjavabyajita.springsecurityjwt.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

     Optional<User> findByEmail(String email);
}
