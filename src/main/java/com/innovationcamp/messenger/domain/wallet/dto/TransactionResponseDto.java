package com.innovationcamp.messenger.domain.wallet.dto;

import com.innovationcamp.messenger.domain.wallet.entity.GroupWallet;
import com.innovationcamp.messenger.domain.wallet.entity.PersonalWallet;
import com.innovationcamp.messenger.domain.wallet.entity.Wallet;
import com.innovationcamp.messenger.domain.wallet.entity.Transaction;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class TransactionResponseDto {
    private String transferType;
    private Long amount;
    private Long balanceBefore;
    private Long balanceAfter;
    private LocalDateTime reservationTime;
    private String reservationState;
    private WalletDto wallet;
    private WalletDto targetWallet;

    public TransactionResponseDto(Transaction transaction) {
        this.transferType = transaction.getTransferType();
        this.amount = transaction.getAmount();
        this.balanceBefore = transaction.getBalanceBefore();
        this.balanceAfter = transaction.getBalanceAfter();
        this.reservationTime = transaction.getReservationTime();
        this.reservationState = transaction.getReservationState();
        this.wallet = new WalletDto(transaction.getWallet());
        this.targetWallet = new WalletDto(transaction.getTargetWallet());
    }

    private static class WalletDto {
        private String owner;

        //개인 통장일 경우 username, 팀 통장일 경우 groupWalletName
        public WalletDto(Wallet wallet) {
            if (wallet instanceof PersonalWallet)
                this.owner = wallet.getUser().getUsername();
            else {
                GroupWallet groupWallet = (GroupWallet) wallet;
                this.owner = groupWallet.getWalletName();
            }
        }
    }

}
