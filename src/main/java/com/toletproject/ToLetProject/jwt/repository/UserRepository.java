package com.toletproject.ToLetProject.jwt.repository;



import com.toletproject.ToLetProject.jwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    @Query(value = "SELECT id FROM User user WHERE username = ?1")
    Optional<String> findAuthUsersById(String username);
}