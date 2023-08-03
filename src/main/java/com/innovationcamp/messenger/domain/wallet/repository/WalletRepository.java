package com.innovationcamp.messenger.domain.wallet.repository;

import com.innovationcamp.messenger.domain.wallet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository<T extends Wallet> extends JpaRepository<T, Long> {
}
