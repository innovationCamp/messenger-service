package com.innovationcamp.messenger.domain.message.repository;

import com.innovationcamp.messenger.domain.message.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByChannelId(Long roomId);
}
