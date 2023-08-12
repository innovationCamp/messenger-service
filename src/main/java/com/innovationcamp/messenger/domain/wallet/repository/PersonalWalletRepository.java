package com.innovationcamp.messenger.domain.wallet.repository;

import com.innovationcamp.messenger.domain.user.entity.User;
import com.innovationcamp.messenger.domain.wallet.entity.PersonalWallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonalWalletRepository extends JpaRepository<PersonalWallet, Long> {
    Optional<PersonalWallet> findByUser(User user);

    boolean existsByUser(User user);
}
