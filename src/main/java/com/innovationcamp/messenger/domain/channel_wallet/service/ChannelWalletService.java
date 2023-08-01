package com.innovationcamp.messenger.domain.channel_wallet.service;

import com.innovationcamp.messenger.domain.channel_wallet.repository.ChannelWalletRepsitory;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class ChannelWalletService {
    @NonNull
    private ChannelWalletRepsitory channelWalletRepsitory;

    public String provideChannelWalletMoney(Long channelWalletId) {
        return "success";
    }

    public String borrowChannelWalletMoney(Long channelWalletId) {
        return "success";
    }

    public String getChannelWalletTransaction(Long channelWalletId) {
        return "success";
    }

    public String getChannelWallet(Long channelWalletId) {
        return "success";
    }

    public String getChannelWalletAllParticipant(Long channelWalletId) {
        return "success";
    }

    public String getAllChannelWallet(Long channelId) {
        return "success";
    }
}
