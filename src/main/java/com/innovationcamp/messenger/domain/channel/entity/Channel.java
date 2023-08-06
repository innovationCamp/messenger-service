package com.innovationcamp.messenger.domain.channel.entity;

import com.innovationcamp.messenger.domain.channel.dto.ChannelCreateDto;
import com.innovationcamp.messenger.domain.channel.dto.ChannelCreateResponseDto;
import com.innovationcamp.messenger.domain.channel.dto.ChannelDto;
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

    @Column
    private String channelPassword;

    @Column
    private String channelDescription;

    @Builder
    public Channel(String channelName, String channelPassword, String channelDescription) {
        this.channelName = channelName;
        this.channelPassword = channelPassword;
        this.channelDescription = channelDescription;
    }

    public Channel(ChannelCreateDto createDto) {
        this.channelName = createDto.getChannelName();
        this.channelPassword = createDto.getChannelPassword();
        this.channelDescription = createDto.getChannelDescription();
    }

    public ChannelCreateResponseDto toResponseDto() {
        return new ChannelCreateResponseDto(id, channelName, channelDescription);
    }

    public ChannelDto toChannelDto() {
        return new ChannelDto(id, channelName, channelDescription);
    }

}
