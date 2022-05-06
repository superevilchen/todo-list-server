package com.example.reversed.repos;

import com.example.reversed.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByEmail(String email);

    Optional<User> findTop1ByEmail(String email);
}
