package com.innovationcamp.messenger.domain.wallet.dto;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class TransactionCreateDto {
    private Long walletId;
    private Long targetWalletId;
    private Long amount;
    private LocalDateTime reservationTime;
}
