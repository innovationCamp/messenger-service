package com.innovationcamp.messenger.domain.wallet.repository;

import com.innovationcamp.messenger.domain.wallet.entity.Transaction;
import com.innovationcamp.messenger.domain.wallet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByWallet(Wallet wallet);
}
