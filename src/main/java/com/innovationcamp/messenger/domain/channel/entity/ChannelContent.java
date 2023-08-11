package com.innovationcamp.messenger.domain.channel.entity;

import com.innovationcamp.messenger.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

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
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "channel_id", nullable = false)
    private Channel channel;

    @ManyToOne
    @JoinColumn(name = "callout_content_id")
    private ChannelContent calloutContent;

    @Column(name = "not_read_count")
    private Long notReadCount;

//    @Builder
//    public ChannelContent(User user, Channel channel, ChannelContent calloutContent,
//                          LocalDateTime createdAt, Long notReadCount) {
//        this.user = user;
//        this.channel = channel;
//        this.calloutContent = calloutContent;
//        this.createdAt = createdAt;
//        this.notReadCount = notReadCount;
//    }

    // 테스트용
    public ChannelContent(User user, Channel channel) {
        this.user = user;
        this.channel = channel;
    }
}