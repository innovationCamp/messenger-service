package com.innovationcamp.messenger.domain.wallet.repository;

import com.innovationcamp.messenger.domain.user.entity.User;
import com.innovationcamp.messenger.domain.wallet.entity.GroupWallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupWalletRepository extends JpaRepository<GroupWallet, Long> {
    List<GroupWallet> findAllByUser(User user);
}
