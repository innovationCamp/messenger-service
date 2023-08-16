package com.innovationcamp.messenger.domain.channel.repository;

import com.innovationcamp.messenger.domain.channel.entity.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChannelRepository extends JpaRepository<Channel, Long> {
    List<Channel> findAllByChannelNameContainingIgnoreCase(String name);

    // count how many users in the channel
    @Query("select count(u) from UserChannel u where u.channel.id = :channelId")
    Long countUserInChannel(@Param("channelId") Long channelId);

}
