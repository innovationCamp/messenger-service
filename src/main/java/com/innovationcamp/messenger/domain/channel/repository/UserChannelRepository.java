package com.innovationcamp.messenger.domain.channel.repository;

import com.innovationcamp.messenger.domain.channel.entity.Channel;
import com.innovationcamp.messenger.domain.channel.entity.UserChannel;
import com.innovationcamp.messenger.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserChannelRepository extends JpaRepository<UserChannel, Long> {
    void deleteByUserAndChannel(User user, Channel channel);
    List<UserChannel> findByUser(User user);
}