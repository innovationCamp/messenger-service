package com.innovationcamp.messenger.domain.channel.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String channelName;
    @OneToMany(mappedBy = "channel")
    private List<UserChannel> userChannelList;

    public Channel(String name) {
        this.channelName = name;
    }
}
