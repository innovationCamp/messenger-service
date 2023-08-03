package com.innovationcamp.messenger.domain.contact.repository;

import com.innovationcamp.messenger.domain.contact.entity.Contact;
import com.innovationcamp.messenger.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> findByUserAndContactUser(User user, User contactUser);

    List<Contact> findAllByUser(User user);
}
