package com.innovationcamp.messenger.domain.channel_wallet.repository;

import com.innovationcamp.messenger.domain.channel_wallet.entity.ChannelWallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelWalletRepsitory extends JpaRepository<Long, ChannelWallet> {
}
