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
@RequestMapping("/api/wallet/personal")
public class PersonalWalletController {
    @NonNull
    private PersonalWalletService walletPersonalService;
    //임시 data
    private User user = new User();

    @GetMapping("")
    public PersonalWallet getPersonalWallet() {
        return walletPersonalService.getPersonalWallet(user);
    }

    @PostMapping("")
    public PersonalWallet createPersonalWallet(@RequestBody PersonalWalletCreateDto requestDto) {
        return walletPersonalService.createPersonalWallet(user, requestDto);
    }

    @DeleteMapping("")
    public PersonalWallet deletePersonalWallet() {
        return walletPersonalService.deletePersonalWallet(user);
    }

    @GetMapping("/transaction")
    public List<TransactionResponseDto> getPersonalWalletTransaction() {
        return walletPersonalService.getPersonalWalletTransaction(user);
    }

    @GetMapping("/group/all")
    public List<GroupWalletResponseDto> getAllGroupWalletByUser() {
        return walletPersonalService.getAllGroupWalletByUser(user);
    }
}
