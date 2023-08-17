package com.innovationcamp.messenger.domain.wallet.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@ToString
public class Transaction extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TransferTypeEnum transferType;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    private Long balanceBefore;

    @Column(nullable = false)
    private Long balanceAfter;

    @Column
    private boolean isReservation;

    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @ManyToOne
    @JoinColumn(name = "target_wallet_id")
    private Wallet targetWallet;

    @Builder
    public Transaction(final TransferTypeEnum transferType, final Long amount, final Long balanceBefore, final Long balanceAfter,
                       final boolean isReservation, final Wallet wallet, final Wallet targetWallet) {
        this.transferType = transferType;
        this.amount = amount;
        this.balanceBefore = balanceBefore;
        this.balanceAfter = balanceAfter;
        this.isReservation = isReservation;
        this.wallet = wallet;
        this.targetWallet = targetWallet;
    }
}
