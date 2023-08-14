package com.innovationcamp.messenger.domain.message.entity;

import com.innovationcamp.messenger.domain.channel.entity.Channel;
import com.innovationcamp.messenger.domain.channel.entity.ChannelContent;
import com.innovationcamp.messenger.domain.message.dto.MessageRequestDto;
import com.innovationcamp.messenger.domain.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
public class Message extends ChannelContent{
    @Column
    private String text;
    @Column
    private MessageRequestDto.MessageType type;
    @Builder
    public Message(User user, Channel channel, Long notReadCount, ChannelContent callOutContent, String message, MessageRequestDto.MessageType type){
        super(user, channel, notReadCount, callOutContent);
        this.text = message;
        this.type = type;
    }
}
