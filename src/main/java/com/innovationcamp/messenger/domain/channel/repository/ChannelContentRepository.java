package com.innovationcamp.messenger.domain.channel.repository;

import com.innovationcamp.messenger.domain.channel.entity.ChannelContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelContentRepository extends JpaRepository<ChannelContent, Long> {
}
