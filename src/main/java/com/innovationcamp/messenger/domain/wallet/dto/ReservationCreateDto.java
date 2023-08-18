package com.innovationcamp.messenger.domain.wallet.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;

import java.time.LocalDate;

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
    private LocalDate date;
}
