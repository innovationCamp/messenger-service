package com.innovationcamp.messenger.domain.channel.entity;

import com.innovationcamp.messenger.domain.channel.dto.*;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Channel extends TimeStamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String channelCreateUserName;

    @Column
    private String channelName;

    @Column
    private String channelPassword;

    @Column
    private String channelDescription;

    @Column
    private Boolean isPrivate = false;

    @Builder
    public Channel(String channelName,
                   String channelCreateUserName,
                   String channelPassword,
                   String channelDescription,
                   Boolean isPrivate) {
        this.channelName = channelName;
        this.channelCreateUserName = channelCreateUserName;
        this.channelPassword = channelPassword;
        this.channelDescription = channelDescription;
        this.isPrivate = isPrivate;
    }

    public void updateChannel(UpdateChannelRequestDto updateChannelDto){
        this.channelName = updateChannelDto.getChannelName();
        this.channelDescription = updateChannelDto.getChannelDescription();
    }

}
