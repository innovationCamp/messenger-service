package com.innovationcamp.messenger.domain.channel.entity;

import com.innovationcamp.messenger.domain.channel.dto.ChannelContentResponseDto;
import com.innovationcamp.messenger.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class ChannelContent {

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

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "not_read_count")
    private Long notReadCount;

    @Builder
    public ChannelContent(User user, Channel channel, ChannelContent calloutContent,
                          LocalDateTime createdAt, Long notReadCount) {
        this.user = user;
        this.channel = channel;
        this.calloutContent = calloutContent;
        this.createdAt = createdAt;
        this.notReadCount = notReadCount;
    }

    public ChannelContentResponseDto toResponseDto(){
        return new ChannelContentResponseDto(
                this.id,
                this.user.getEmail(),
                this.user.getUsername(),
                this.calloutContent == null ? null : this.calloutContent.getId(),
                this.createdAt,
                this.notReadCount
        );
    }

}