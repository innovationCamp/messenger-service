package com.innovationcamp.messenger.domain.channel.repository;

import com.innovationcamp.messenger.domain.channel.entity.Channel;
import com.innovationcamp.messenger.domain.channel.entity.ChannelContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChannelContentRepository extends JpaRepository<ChannelContent, Long> {
    List<ChannelContent> findByChannel(Channel channel);
}