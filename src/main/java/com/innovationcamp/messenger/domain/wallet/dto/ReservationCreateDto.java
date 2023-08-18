package com.innovationcamp.messenger.domain.wallet.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReservationCreateDto {
    public enum ReservationType {
        MONTHLY, DAILY
    }
    private ReservationType type; // 예약 타입
    private Long walletId;
    private Long targetWalletId;
    private Long amount;
    private String password;
    private LocalDateTime reservationTime;
}
