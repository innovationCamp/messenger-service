package com.innovationcamp.messenger.domain.user.repository;

import com.innovationcamp.messenger.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
