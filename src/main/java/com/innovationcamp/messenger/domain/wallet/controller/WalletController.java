package com.innovationcamp.messenger.domain.wallet.controller;

import com.innovationcamp.messenger.domain.wallet.service.WalletService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/wallet")
public class WalletController {
    @NonNull
    private WalletService walletService;

    @GetMapping("")
    public String getWallet(){
        return walletService.getWallet();
    }

    @GetMapping("/transaction")
    public String getWalletTransaction(){
        return walletService.getWalletTransaction();
    }

    @PostMapping("/provide")
    public String provideWalletMoney(){
        return walletService.provideWalletMoney();
    }
}
