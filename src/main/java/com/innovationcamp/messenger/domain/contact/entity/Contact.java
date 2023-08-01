package com.innovationcamp.messenger.domain.contact.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long contactId;

    public Contact(Long userId, Long addId) {
        this.userId = userId;
        this.contactId = addId;
    }
}

