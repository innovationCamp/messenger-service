package com.innovationcamp.messenger.domain.wallet.entity;

import com.innovationcamp.messenger.domain.wallet.dto.ReservationCreateDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@ToString
@Getter
public class Reservation extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long amount;

    @Column
    private LocalDateTime reservationTime;

    @Column
    @Enumerated(value = EnumType.STRING)
    private ReservationCreateDto.ReservationType reservationType;

    @Column
    @Enumerated(value = EnumType.STRING)
    private ReservationStateEnum reservationState;

    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @ManyToOne
    @JoinColumn(name = "target_wallet_id")
    private Wallet targetWallet;

    @Builder
    public Reservation(Wallet wallet,
                       Wallet targetWallet,
                       Long amount,
                       LocalDateTime reservationTime,
                       ReservationCreateDto.ReservationType reservationType,
                       ReservationStateEnum reservationState){
        this.amount = amount;
        this.reservationTime = reservationTime;
        this.reservationType = reservationType;
        this.reservationState = reservationState;
    }

    public void updateStatus(ReservationStateEnum reservationStateEnum) {
        this.reservationState = reservationStateEnum;
    }
}
