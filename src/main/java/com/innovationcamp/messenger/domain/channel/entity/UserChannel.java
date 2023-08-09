package com.innovationcamp.messenger.domain.channel.entity;

import com.innovationcamp.messenger.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class UserChannel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // UserChannel이 주인인 단방향관계
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
    
    // UserChannel이 주인인 단방향관계
    @ManyToOne
    @JoinColumn(name="channel_id", nullable=false)
    private Channel channel;

    @Column
    private LocalDateTime readTimestamp;

    @Column
    private boolean isAdmin;

    @Builder
    public UserChannel(User user, Channel channel, LocalDateTime readTimestamp, boolean isAdmin) {
        this.user = user;
        this.channel = channel;
        this.readTimestamp = readTimestamp;
        this.isAdmin = isAdmin;
    }

}
