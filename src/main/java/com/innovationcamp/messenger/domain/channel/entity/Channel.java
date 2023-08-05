package com.innovationcamp.messenger.domain.channel.entity;

import jakarta.persistence.*;
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
}
