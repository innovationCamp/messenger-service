package com.innovationcamp.messenger.domain.channel_wallet.controller;

import com.innovationcamp.messenger.domain.channel_wallet.service.ChannelWalletService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/channel-wallet")
public class ChannelWalletController {
    @NonNull
    private ChannelWalletService channelWalletService;

    @PostMapping("/{channelWalletId}/provide")
    public String provideChannelWalletMoney(@PathVariable Long channelWalletId){
        return channelWalletService.provideChannelWalletMoney(channelWalletId);
    }

    @PostMapping("/{channelWalletId}/borrow")
    public String borrowChannelWalletMoney(@PathVariable Long channelWalletId){
        return channelWalletService.borrowChannelWalletMoney(channelWalletId);
    }

    @GetMapping("/{channelWalletId}")
    public String getChannelWallet(@PathVariable Long channelWalletId){
        return channelWalletService.getChannelWallet(channelWalletId);
    }

    @GetMapping("/{channelWalletId}/transaction")
    public String getChannelWalletTransaction(@PathVariable Long channelWalletId){
        return channelWalletService.getChannelWalletTransaction(channelWalletId);
    }

    @GetMapping("/{channelWalletId}/participant")
    public String getChannelWalletAllParticipant(@PathVariable Long channelWalletId){
        return channelWalletService.getChannelWalletAllParticipant(channelWalletId);
    }

    @GetMapping("/{channelId}/all")
    public String getAllChannelWallet(@PathVariable Long channelId){
        return channelWalletService.getAllChannelWallet(channelId);
    }
}
