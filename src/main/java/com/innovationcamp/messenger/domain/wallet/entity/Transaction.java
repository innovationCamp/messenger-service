package com.innovationcamp.messenger.domain.wallet.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@ToString
public class Transaction extends TimeStamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String transferType;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    private Long balanceBefore;

    @Column(nullable = false)
    private Long balanceAfter;

    @Column
    private LocalDateTime reservationTime;

    @Column
    private String reservationState;

    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @ManyToOne
    @JoinColumn(name = "target_wallet_id")
    private Wallet targetWallet;
}
