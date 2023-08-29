package com.innovationcamp.messenger.domain.message.mongodb.repository;

import com.innovationcamp.messenger.domain.message.mongodb.entity.MongoChannelContent;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MongoChannelContentRepository extends MongoRepository<MongoChannelContent, Long> {
    List<MongoChannelContent> findAllByChannelId(Long channelId);
}
