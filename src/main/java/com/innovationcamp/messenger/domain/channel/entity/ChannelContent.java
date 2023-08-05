package com.innovationcamp.messenger.domain.channel.entity;

import com.innovationcamp.messenger.domain.user.entity.User;
import com.innovationcamp.messenger.domain.wallet.entity.TimeStamped;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor
@ToString
@DiscriminatorColumn
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ChannelContent extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "channel_id")
    private Channel channel;

    public ChannelContent(User user, Channel channel) {
        this.user = user;
        this.channel = channel;
    }
}


