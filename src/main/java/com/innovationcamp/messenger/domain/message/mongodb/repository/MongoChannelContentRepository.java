package com.innovationcamp.messenger.domain.message.mongodb.repository;

import com.innovationcamp.messenger.domain.message.mongodb.entity.MongoChannelContent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoChannelContentRepository extends MongoRepository<MongoChannelContent, Long> {
}
