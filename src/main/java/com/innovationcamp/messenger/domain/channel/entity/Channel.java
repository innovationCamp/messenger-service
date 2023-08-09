package com.innovationcamp.messenger.domain.channel.entity;

import com.innovationcamp.messenger.domain.channel.dto.*;
import com.innovationcamp.messenger.domain.user.entity.User;
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
    private String channelName;

    @ManyToOne
    @JoinColumn(name = "channel_create_user_id", referencedColumnName = "id")
    private User channelCreateUser;

    @Column
    private String channelPassword;

    @Column
    private String channelDescription;

    @Column
    private Boolean isPrivate;

    @Builder
    public Channel(String channelName,
                   User channelCreateUser,
                   String channelPassword,
                   String channelDescription,
                   Boolean isPrivate) {
        this.channelName = channelName;
        this.channelCreateUser = channelCreateUser;
        this.channelPassword = channelPassword;
        this.channelDescription = channelDescription;
        this.isPrivate = isPrivate;
    }

    public void updateChannel(UpdateChannelRequestDto updateChannelDto){
        this.channelName = updateChannelDto.getChannelName();
        this.channelDescription = updateChannelDto.getChannelDescription();
    }

}
