package com.learning.crudsample.infrastructure.security;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    @EntityGraph(attributePaths = "roles")
    Optional<User> findByEmail(String email);

}
