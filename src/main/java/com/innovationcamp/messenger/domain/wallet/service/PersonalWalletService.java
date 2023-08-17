package com.innovationcamp.messenger.domain.wallet.service;

import com.innovationcamp.messenger.domain.user.entity.User;
import com.innovationcamp.messenger.domain.wallet.config.WalletPasswordEncoder;
import com.innovationcamp.messenger.domain.wallet.dto.*;
import com.innovationcamp.messenger.domain.wallet.entity.PersonalWallet;
import com.innovationcamp.messenger.domain.wallet.entity.Transaction;
import com.innovationcamp.messenger.domain.wallet.entity.UserGroupWallet;
import com.innovationcamp.messenger.domain.wallet.repository.PersonalWalletRepository;
import com.innovationcamp.messenger.domain.wallet.repository.ReservationRepository;
import com.innovationcamp.messenger.domain.wallet.repository.TransactionRepository;
import com.innovationcamp.messenger.domain.wallet.repository.UserGroupWalletRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PersonalWalletService {
    @NonNull
    private WalletPasswordEncoder walletPasswordEncoder;
    @NonNull
    private PersonalWalletRepository personalWalletRepository;
    @NonNull
    private TransactionRepository transactionRepository;
    @NonNull
    private UserGroupWalletRepository userGroupWalletRepository;
    @NonNull
    private ReservationRepository reservationRepository;

    //추천인 받고 들어오면 시작머니 있다던가
    private final Long money = 0L;

    private PersonalWallet findPersonalWalletByUser(User user) {
        return personalWalletRepository.findByUser(user).orElseThrow(() -> new IllegalArgumentException("Wallet 을 개설하지 않은 유저입니다."));
    }

    public PersonalWalletResponseDto getPersonalWallet(User user) {
        return new PersonalWalletResponseDto(findPersonalWalletByUser(user));
    }

    public PersonalWalletResponseDto createPersonalWallet(User user, PersonalWalletCreateDto requestDto) {
        if (personalWalletRepository.existsByUser(user)){
            throw new IllegalArgumentException("이미 Wallet 을 개설한 유저입니다.");
        }
        PersonalWallet personalWallet = new PersonalWallet(money, walletPasswordEncoder.encode(requestDto.getPassword()), user);
        return new PersonalWalletResponseDto(personalWalletRepository.save(personalWallet));
    }

    public String  deletePersonalWallet(User user) {
        PersonalWallet personalWallet = findPersonalWalletByUser(user);
        personalWalletRepository.delete(personalWallet);
        return "개인통장이 해지되었습니다.";
    }

    public List<TransactionResponseDto> getAllTransactionByPersonalWallet(User user) {
        PersonalWallet personalWallet = findPersonalWalletByUser(user);
        List<Transaction> transactionList = transactionRepository.findAllByWallet(personalWallet);
        return transactionList.stream().map(TransactionResponseDto::new).collect(Collectors.toList());
    }

    public List<GroupWalletResponseDto> getAllGroupWalletByUser(User user) {
        List<UserGroupWallet> userGroupWalletList = userGroupWalletRepository.findAllByUser(user);
        return userGroupWalletList.stream().map(w -> new GroupWalletResponseDto(w.getGroupWallet())).collect(Collectors.toList());
    }

    public List<ReservationResponseDto> getAllReservationByPersonalWallet(User user) {
        PersonalWallet wallet = findPersonalWalletByUser(user);
        return reservationRepository.findAllByWalletId(wallet.getId()).stream().map(ReservationResponseDto::new).toList();
    }
}
