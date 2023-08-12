package com.innovationcamp.messenger.domain.channel.dto;

import com.innovationcamp.messenger.domain.channel.entity.Channel;
import com.innovationcamp.messenger.domain.channel.entity.UserChannel;
import com.innovationcamp.messenger.domain.user.entity.User;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ParticipantChannelDto {
    Channel channel;
    User user;
    boolean admin;

    public ParticipantChannelDto(UserChannel userChannel){
        this.channel = userChannel.getChannel();
        this.user = userChannel.getUser();
        this.admin = userChannel.isAdmin();
    }
}
