package com.innovationcamp.messenger.domain.wallet.repository;

import com.innovationcamp.messenger.domain.user.entity.User;
import com.innovationcamp.messenger.domain.wallet.entity.GroupWallet;
import com.innovationcamp.messenger.domain.wallet.entity.GroupWalletUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupWalletUserRepository extends JpaRepository<GroupWalletUser, Long> {
    void deleteByGroupWallet(GroupWallet groupWallet);
    List<GroupWalletUser> findAllByGroupWallet(GroupWallet groupWallet);
    List<GroupWalletUser> findAllByUser(User user);
    Optional<GroupWalletUser> findByUserAndGroupWallet(User user, GroupWallet groupWallet);
}
