package com.innovationcamp.messenger.domain.channel.dto;

import com.innovationcamp.messenger.domain.channel.entity.Channel;
import com.innovationcamp.messenger.domain.channel.entity.UserChannel;
import com.innovationcamp.messenger.domain.user.entity.User;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ParticipantChannelDto {
    Long channelId;
    String userName;
    String userEmail;
    boolean admin;

    public ParticipantChannelDto(UserChannel userChannel){
        this.channelId = userChannel.getChannel().getId();
        this.userName = userChannel.getUser().getUsername();
        this.userEmail = userChannel.getUser().getEmail();
        this.admin = userChannel.isAdmin();
    }
}
