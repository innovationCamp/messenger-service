package com.innovationcamp.messenger.domain.wallet.service;

import com.innovationcamp.messenger.domain.user.entity.User;
import com.innovationcamp.messenger.domain.wallet.config.WalletPasswordEncoder;
import com.innovationcamp.messenger.domain.wallet.dto.TransactionCreateDto;
import com.innovationcamp.messenger.domain.wallet.dto.TransactionResponseDto;
import com.innovationcamp.messenger.domain.wallet.entity.*;
import com.innovationcamp.messenger.domain.wallet.repository.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WalletService {
    @NonNull
    private WalletRepository<Wallet> walletRepository;
    @NonNull
    private TransactionRepository transactionRepository;
    @NonNull
    private UserGroupWalletRepository userGroupWalletRepository;
    @NonNull
    private GroupSpendDetailRepository groupSpendDetailRepository;
    @NonNull
    private WalletPasswordEncoder walletPasswordEncoder;

    private void validateBeforeTransaction(User user, Wallet wallet, TransactionCreateDto requestDto) {
        if (!walletPasswordEncoder.checkPassword(requestDto.getPassword(), wallet.getPassword())) throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        if (user.equals(wallet.getUser())) {
            if (wallet.getMoney() < requestDto.getAmount()) throw new IllegalArgumentException("잔액이 부족합니다.");
            return;
        } else if (wallet instanceof GroupWallet groupWallet) {
            UserGroupWallet userGroupWallet = userGroupWalletRepository.findByUserAndGroupWallet(user, groupWallet).orElseThrow(() -> new IllegalArgumentException("groupWallet 에 참여하지 않은 user 입니다."));
            if (!userGroupWallet.getUserAuthority().equals(UserAuthorityEnum.ADMIN))
                throw new IllegalArgumentException("groupWallet 에 권한이 없는 user 입니다.");
            if (groupWallet.getMoney() < requestDto.getAmount()) throw new IllegalArgumentException("잔액이 부족합니다.");
            return;
        }
        throw new IllegalArgumentException("부적절한 송금입니다.");
    }

    /*
     송금 가능 조건 ( validateBeforeTransaction )
     1. 송금할 수 있는 유저 - wallet 주인또는 groupWallet 이라면 참여자이고 적절한 authority 필요
     2. 송금 가능한 금액

     transaction 테이블 insert 이외에 추가 경우
     1. groupWallet 의 송금이라면 GroupSpendDetail insert
     2. 예약이라면 Reservation insert

     예약시 추가 고려사항
     1. 동시간대 예약의 경우 우선 순위
     2. 예약 성공/실패시 reservationState update 필요
    */
    @Transactional
    public TransactionResponseDto createTransaction(User user, final TransactionCreateDto requestDto) {
        final Wallet wallet = walletRepository.findById(requestDto.getWalletId()).orElseThrow(() -> new IllegalArgumentException("부적절한 Wallet 입니다."));
        validateBeforeTransaction(user, wallet, requestDto);
        if (requestDto.getReservationTime() != null) throw new NullPointerException("현재 예약 송금 기능 미구현");

        final Wallet targetWallet = walletRepository.findById(requestDto.getTargetWalletId()).orElseThrow(() -> new IllegalArgumentException("부적절한 TargetWallet 입니다."));
        final Long balanceAfterSend = wallet.getMoney() - requestDto.getAmount();
        Transaction transactionSend = Transaction.builder()
                .transferType(TransferTypeEnum.SEND)
                .amount(requestDto.getAmount())
                .balanceBefore(wallet.getMoney())
                .balanceAfter(balanceAfterSend)
                .wallet(wallet)
                .targetWallet(targetWallet)
                .build();

        final Long balanceAfterReceive = targetWallet.getMoney() + requestDto.getAmount();
        Transaction transactionReceive = Transaction.builder()
                .transferType(TransferTypeEnum.RECEIVE)
                .amount(requestDto.getAmount())
                .balanceBefore(wallet.getMoney())
                .balanceAfter(balanceAfterReceive)
                .wallet(targetWallet)
                .targetWallet(wallet)
                .build();

        wallet.update(balanceAfterSend);
        targetWallet.update(balanceAfterReceive);

        List<Transaction> transactionList = Arrays.asList(transactionSend, transactionReceive);
        transactionRepository.saveAll(transactionList);
        if (wallet instanceof GroupWallet groupWallet) {
            GroupSpendDetail groupSpendDetail = new GroupSpendDetail(transactionSend, groupWallet, user);
            groupSpendDetailRepository.save(groupSpendDetail);
        }
        return new TransactionResponseDto(transactionSend);
    }
}
