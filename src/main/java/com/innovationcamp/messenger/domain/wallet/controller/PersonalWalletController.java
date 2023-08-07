package com.innovationcamp.messenger.domain.wallet.controller;

import com.innovationcamp.messenger.domain.user.entity.User;
import com.innovationcamp.messenger.domain.wallet.dto.GroupWalletResponseDto;
import com.innovationcamp.messenger.domain.wallet.dto.PersonalWalletCreateDto;
import com.innovationcamp.messenger.domain.wallet.dto.TransactionResponseDto;
import com.innovationcamp.messenger.domain.wallet.entity.PersonalWallet;
import com.innovationcamp.messenger.domain.wallet.service.PersonalWalletService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/wallet/user")
public class PersonalWalletController {
    @NonNull
    private PersonalWalletService walletPersonalService;

    @GetMapping("")
    public PersonalWallet getPersonalWallet(@ModelAttribute User user) {
        return walletPersonalService.getPersonalWallet(user);
    }

    @PostMapping("")
    public PersonalWallet createPersonalWallet(@ModelAttribute User user, @RequestBody PersonalWalletCreateDto requestDto) {
        return walletPersonalService.createPersonalWallet(user, requestDto);
    }

    @DeleteMapping("")
    public PersonalWallet deletePersonalWallet(@ModelAttribute User user) {
        return walletPersonalService.deletePersonalWallet(user);
    }

    @GetMapping("/transaction/all")
    public List<TransactionResponseDto> getAllTransactionByPersonalWallet(@ModelAttribute User user) {
        return walletPersonalService.getAllTransactionByPersonalWallet(user);
    }

    @GetMapping("/group/all")
    public List<GroupWalletResponseDto> getAllGroupWalletByUser(@ModelAttribute User user) {
        return walletPersonalService.getAllGroupWalletByUser(user);
    }
}
