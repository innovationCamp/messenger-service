package com.innovationcamp.messenger.domain.wallet.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
@Getter
public class ReservationCreateDto {
    public enum ReservationType {
        MONTHLY, DAILY
    }
    private ReservationType type; // 예약 타입
    private Long walletId;
    private Long targetWalletId;
    private Long amount;
    @Min(value = 2023, message = "2023년 이전은 불가능 합니다.")
    @Max(value = 2050, message = "2050년 이후는 불가능 합니다.")
    private int year;
    @Min(value = 1, message = "1월 보다 작을 수 없습니다.")
    @Max(value = 12, message = "12월 보다 클 수 없습니다.")
    private int month;
    @Min(value = 1, message = "1일 보다 작을 수 없습니다.")
    @Max(value = 31, message = "31일 보다 클 수 없습니다.")
    private int day;
    private String password;
}
