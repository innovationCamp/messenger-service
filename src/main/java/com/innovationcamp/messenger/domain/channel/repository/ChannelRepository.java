package com.innovationcamp.messenger.domain.channel.repository;

import com.innovationcamp.messenger.domain.channel.entity.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChannelRepository extends JpaRepository<Channel, Long> {
    List<Channel> findAllByChannelNameContainingIgnoreCase(String name);
}
