package com.innovationcamp.messenger.domain.wallet.service;

import com.innovationcamp.messenger.domain.user.entity.User;
import com.innovationcamp.messenger.domain.wallet.config.WalletPasswordEncoder;
import com.innovationcamp.messenger.domain.wallet.dto.ReservationCreateDto;
import com.innovationcamp.messenger.domain.wallet.dto.ReservationResponseDto;
import com.innovationcamp.messenger.domain.wallet.dto.TransactionCreateDto;
import com.innovationcamp.messenger.domain.wallet.dto.TransactionResponseDto;
import com.innovationcamp.messenger.domain.wallet.entity.*;
import com.innovationcamp.messenger.domain.wallet.repository.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.innovationcamp.messenger.domain.wallet.entity.UserAuthorityEnum.Authority.user;

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
    @NonNull
    private ReservationRepository reservationRepository;

    private void validateUser(User user, Wallet wallet) {
        if (user.equals(wallet.getUser())) {
            return;
        } else if (wallet instanceof GroupWallet groupWallet) {
            UserGroupWallet userGroupWallet = userGroupWalletRepository.findByUserAndGroupWallet(user, groupWallet).orElseThrow(() -> new IllegalArgumentException("groupWallet 에 참여하지 않은 user 입니다."));
            if (!userGroupWallet.getUserAuthority().equals(UserAuthorityEnum.ADMIN))
                throw new IllegalArgumentException("groupWallet 에 권한이 없는 user 입니다.");
            return;
        }
        throw new IllegalArgumentException("부적절한 송금입니다.");
    }
    private void checkBalance(Long amount, Wallet wallet){
        if (wallet.getMoney() < amount) throw new IllegalArgumentException("잔액이 부족합니다.");
    }
    private void checkPassword(String password, Wallet wallet){
        if (!walletPasswordEncoder.checkPassword(password, wallet.getPassword())) throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
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
        validateUser(user, wallet);
        checkPassword(requestDto.getPassword(), wallet);
        checkBalance(requestDto.getAmount(), wallet);
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
                .balanceBefore(targetWallet.getMoney())
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

    public LocalDateTime createReservationTime(ReservationCreateDto requestDto) {
        int randomHour = generateRandomNumberInRange(10, 14); // 10부터 14까지 랜덤 시간
        int randomMinute = generateRandomNumberInRange(0, 59); // 0부터 59까지 랜덤 분
        int second = 0; // 고정 값으로 0

        LocalDateTime reservationTime = LocalDateTime.of(requestDto.getYear(), requestDto.getMonth(), requestDto.getDay(), randomHour, randomMinute, second);

        return reservationTime;
    }

    private int generateRandomNumberInRange(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    public ReservationResponseDto createReservation(User user, ReservationCreateDto requestDto) {
        final Wallet wallet = walletRepository.findById(requestDto.getWalletId()).orElseThrow(() -> new IllegalArgumentException("부적절한 Wallet 입니다."));
        validateUser(user, wallet);
        checkPassword(requestDto.getPassword(), wallet);
        final Wallet targetWallet = walletRepository.findById(requestDto.getTargetWalletId()).orElseThrow(() -> new IllegalArgumentException("부적절한 TargetWallet 입니다."));
        LocalDateTime reservationTime = createReservationTime(requestDto);
        Reservation reservation = Reservation.builder()
                .wallet(wallet)
                .targetWallet(targetWallet)
                .amount(requestDto.getAmount())
                .reservationTime(reservationTime)
                .reservationType(requestDto.getType())
                .reservationState(ReservationStateEnum.RESERVATION)
                .user(user)
                .build();
        reservationRepository.save(reservation);
        return new ReservationResponseDto(reservation);
    }

    @Transactional
    public boolean reservationTransaction(Reservation reservation){
        final Wallet wallet = reservation.getWallet();
        final Wallet targetWallet = reservation.getTargetWallet();

        checkBalance(reservation.getAmount(), wallet);
        final Long balanceAfterSend = wallet.getMoney() - reservation.getAmount();

        Transaction transactionSend = Transaction.builder()
                .transferType(TransferTypeEnum.SEND)
                .amount(reservation.getAmount())
                .balanceBefore(wallet.getMoney())
                .balanceAfter(balanceAfterSend)
                .wallet(wallet)
                .targetWallet(targetWallet)
                .isReservation(true)
                .build();

        final Long balanceAfterReceive = targetWallet.getMoney() + reservation.getAmount();
        Transaction transactionReceive = Transaction.builder()
                .transferType(TransferTypeEnum.RECEIVE)
                .amount(reservation.getAmount())
                .balanceBefore(targetWallet.getMoney())
                .balanceAfter(balanceAfterReceive)
                .wallet(targetWallet)
                .targetWallet(wallet)
                .build();

        wallet.update(balanceAfterSend);
        targetWallet.update(balanceAfterReceive);

        List<Transaction> transactionList = Arrays.asList(transactionSend, transactionReceive);
        transactionRepository.saveAll(transactionList);
        /* 일단 빼고 구현
        if (wallet instanceof GroupWallet groupWallet) {
            GroupSpendDetail groupSpendDetail = new GroupSpendDetail(transactionSend, groupWallet, user);
            groupSpendDetailRepository.save(groupSpendDetail);
        }

        reservation.updateTransaction(transactionSend);
        return true;
    }
}
