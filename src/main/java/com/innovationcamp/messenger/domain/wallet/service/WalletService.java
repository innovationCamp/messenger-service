package com.innovationcamp.messenger.domain.wallet.service;

import com.innovationcamp.messenger.domain.wallet.repository.WalletRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WalletService {
    @NonNull
    private WalletRepository walletRepository;

    public String getWallet() {
        return "success";
    }

    public String getWalletTransaction() {
        return "success";
    }

    public String provideWalletMoney() {
        return "success";
    }
}
