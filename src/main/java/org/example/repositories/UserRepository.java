package org.example.repositories;

import org.example.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String userName);

    boolean existsByEmail(String email);
}
