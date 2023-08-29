package com.innovationcamp.messenger.domain.message.mongodb.repository;

import com.innovationcamp.messenger.domain.message.mongodb.entity.MongoMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoMessageRepository extends MongoRepository<MongoMessage, Long> {
}
