package com.innovationcamp.messenger.domain.user.repository;

import com.innovationcamp.messenger.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String sender);
}
