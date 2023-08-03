package com.innovationcamp.messenger.domain.contact.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "contact_id", nullable = false)
    private Long contactId;

    public Contact(Long userId, Long addId) {
        this.userId = userId;
        this.contactId = addId;
    }
}

