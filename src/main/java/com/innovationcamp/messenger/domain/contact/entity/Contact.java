package com.innovationcamp.messenger.domain.contact.entity;

import com.innovationcamp.messenger.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Contact {
    // user - contact -user
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "contact_user_id")
    private User contactUser;

    public Contact(User user, User contactUser) {
        this.user = user;
        this.contactUser = contactUser;
    }
}

