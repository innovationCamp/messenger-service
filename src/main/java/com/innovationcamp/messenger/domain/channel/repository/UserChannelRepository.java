package com.innovationcamp.messenger.domain.channel.repository;

import com.innovationcamp.messenger.domain.channel.entity.Channel;
import com.innovationcamp.messenger.domain.channel.entity.UserChannel;
import com.innovationcamp.messenger.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserChannelRepository extends JpaRepository<UserChannel, Long> {
    void deleteByUserAndChannel(User user, Channel channel);

    Optional<UserChannel> findByUserAndChannel(User user, Channel channel);
    Optional<UserChannel> findByUserAndChannelId(User user, Long channelId);
    List<UserChannel> findByUser(User user);
    List<UserChannel> findByChannel(Channel channel);

    boolean existsByChannelIdAndUserId(Long channelId, Long userId);
    Long countByChannelId(Long channelId);
}