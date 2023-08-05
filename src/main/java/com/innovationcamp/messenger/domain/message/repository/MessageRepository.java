package com.innovationcamp.messenger.domain.message.repository;

import com.innovationcamp.messenger.domain.message.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
