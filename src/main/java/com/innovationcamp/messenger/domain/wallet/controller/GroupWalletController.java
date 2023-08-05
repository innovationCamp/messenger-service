package com.innovationcamp.messenger.domain.wallet.controller;

import com.innovationcamp.messenger.domain.user.entity.User;
import com.innovationcamp.messenger.domain.wallet.dto.GroupWalletCreateDto;
import com.innovationcamp.messenger.domain.wallet.dto.TransactionResponseDto;
import com.innovationcamp.messenger.domain.wallet.dto.WalletUserResponseDto;
import com.innovationcamp.messenger.domain.wallet.entity.GroupWallet;
import com.innovationcamp.messenger.domain.wallet.service.GroupWalletService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wallet/group")
@RequiredArgsConstructor
public class GroupWalletController {
    @NonNull
    private GroupWalletService groupWalletService;

    @PostMapping("")
    public GroupWallet createGroupWallet(@ModelAttribute User user, @RequestBody GroupWalletCreateDto requestDto) {
        return groupWalletService.createGroupWallet(user, requestDto);
    }

    @GetMapping("/{groupWalletId}")
    public GroupWallet getGroupWalletById(@PathVariable Long groupWalletId) {
        return groupWalletService.getGroupWalletById(groupWalletId);
    }

    @PostMapping("/{groupWalletId}")
    public GroupWallet participantGroupWalletById(@ModelAttribute User user, @PathVariable Long groupWalletId){
        return groupWalletService.participantGroupWalletById(user, groupWalletId);
    }

    @DeleteMapping("/{groupWalletId}")
    public GroupWallet deleteGroupWalletById(@PathVariable Long groupWalletId) {
        return groupWalletService.deleteGroupWalletById(groupWalletId);
    }

    @GetMapping("/{groupWalletId}/transaction/all")
    public List<TransactionResponseDto> getAllTransactionByGroupWallet(@PathVariable Long groupWalletId) {
        return groupWalletService.getTransactionByGroupWallet(groupWalletId);
    }

    @GetMapping("/{groupWalletId}/participant/all")
    public List<WalletUserResponseDto> getAllParticipantByGroupWallet(@PathVariable Long groupWalletId) {
        return groupWalletService.getAllParticipantByGroupWallet(groupWalletId);
    }
}
