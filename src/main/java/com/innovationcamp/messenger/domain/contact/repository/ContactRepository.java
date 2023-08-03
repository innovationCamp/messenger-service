package com.innovationcamp.messenger.domain.contact.repository;

import com.innovationcamp.messenger.domain.contact.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    @Query(value = "SELECT * FROM contact WHERE user_id = :userId AND contact_user_id = :contactUserId", nativeQuery = true)
    Optional<Contact> findByUserIdAndContactUserId(Long userId, Long contactUserId);
    List<Contact> findByUserId(Long userId);

}
