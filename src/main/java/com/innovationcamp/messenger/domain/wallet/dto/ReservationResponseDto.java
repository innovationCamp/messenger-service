package com.innovationcamp.messenger.domain.wallet.dto;

import com.innovationcamp.messenger.domain.wallet.entity.Reservation;
import com.innovationcamp.messenger.domain.wallet.entity.ReservationStateEnum;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReservationResponseDto {
    private Long id;
    private Long walletId;
    private Long targetWalletId;
    private Long amount;
    private LocalDateTime reservationTime;
    private ReservationCreateDto.ReservationType reservationType; // 예약 타입
    private ReservationStateEnum reservationState;
    private String username;

    public ReservationResponseDto(Reservation reservation){
        this.id = reservation.getId();
        this.walletId = reservation.getWallet().getId();
        this.targetWalletId = reservation.getTargetWallet().getId();
        this.amount = reservation.getAmount();
        this.reservationTime = reservation.getReservationTime();
        this.reservationType = reservation.getReservationType();
        this.reservationState = reservation.getReservationState();
        this.username = reservation.getUser().getUsername();
    }
}
