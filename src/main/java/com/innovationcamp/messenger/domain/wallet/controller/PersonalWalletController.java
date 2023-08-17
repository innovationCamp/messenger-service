package com.innovationcamp.messenger.domain.wallet.controller;

import com.innovationcamp.messenger.domain.user.entity.User;
import com.innovationcamp.messenger.domain.wallet.dto.*;
import com.innovationcamp.messenger.domain.wallet.service.PersonalWalletService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/wallet/user")
public class PersonalWalletController {
    @NonNull
    private PersonalWalletService walletPersonalService;

    @GetMapping("")
    public PersonalWalletResponseDto getPersonalWallet(@RequestAttribute User user) {
        return walletPersonalService.getPersonalWallet(user);
    }

    @PostMapping("")
    public PersonalWalletResponseDto createPersonalWallet(@RequestAttribute User user, @RequestBody PersonalWalletCreateDto requestDto) {
        return walletPersonalService.createPersonalWallet(user, requestDto);
    }

    @DeleteMapping("")
    public String deletePersonalWallet(@RequestAttribute User user) {
        return walletPersonalService.deletePersonalWallet(user);
    }

    @GetMapping("/transaction/all")
    public List<TransactionResponseDto> getAllTransactionByPersonalWallet(@RequestAttribute User user) {
        return walletPersonalService.getAllTransactionByPersonalWallet(user);
    }

    @GetMapping("/group/all")
    public List<GroupWalletResponseDto> getAllGroupWalletByUser(@RequestAttribute User user) {
        return walletPersonalService.getAllGroupWalletByUser(user);
    }

    @GetMapping("/reservation")
    public List<ReservationResponseDto> getAllReservationByPersonalWallet(@RequestAttribute User user){
        return walletPersonalService.getAllReservationByPersonalWallet(user);
    }

    @Operation(summary = "개인통장에 입금하기",
            description = "Wallet 관련 테스트를 위해 원하는 금액만큼 입금 할 수 있게 구현했습니다.")
    @PostMapping("/money")
    public PersonalWalletResponseDto createMoney(@RequestAttribute User user,
                                                 @RequestBody PersonalWalletCreateMoneyDto requestDto){
        return walletPersonalService.createMoney(user, requestDto.getAmount());
    }
}
