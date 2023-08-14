package com.innovationcamp.messenger.domain.wallet.repository;

import com.innovationcamp.messenger.domain.user.entity.User;
import com.innovationcamp.messenger.domain.wallet.entity.GroupWallet;
import com.innovationcamp.messenger.domain.wallet.entity.UserGroupWallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserGroupWalletRepository extends JpaRepository<UserGroupWallet, Long> {
    void deleteByGroupWallet(GroupWallet groupWallet);
    List<UserGroupWallet> findAllByGroupWallet(GroupWallet groupWallet);
    List<UserGroupWallet> findAllByUser(User user);
    Optional<UserGroupWallet> findByUserAndGroupWallet(User user, GroupWallet groupWallet);
    boolean existsByGroupWalletIdAndUserId(Long groupWalletId, Long id);
}
