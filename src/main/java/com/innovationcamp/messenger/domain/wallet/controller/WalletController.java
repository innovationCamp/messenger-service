package com.innovationcamp.messenger.domain.wallet.controller;

import com.innovationcamp.messenger.domain.user.entity.User;
import com.innovationcamp.messenger.domain.wallet.dto.TransactionCreateDto;
import com.innovationcamp.messenger.domain.wallet.dto.TransactionResponseDto;
import com.innovationcamp.messenger.domain.wallet.service.WalletService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/wallet")
public class WalletController {
    @NonNull
    private WalletService walletService;

    @PostMapping("/transaction")
    public TransactionResponseDto createTransaction(@ModelAttribute User user, @RequestBody TransactionCreateDto requestDto) {
        return walletService.createTransaction(user, requestDto);
    }
}
