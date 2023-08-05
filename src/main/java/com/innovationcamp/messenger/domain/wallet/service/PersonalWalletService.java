package com.innovationcamp.messenger.domain.wallet.service;

import com.innovationcamp.messenger.domain.user.entity.User;
import com.innovationcamp.messenger.domain.wallet.config.WalletPasswordEncoder;
import com.innovationcamp.messenger.domain.wallet.dto.GroupWalletResponseDto;
import com.innovationcamp.messenger.domain.wallet.dto.PersonalWalletCreateDto;
import com.innovationcamp.messenger.domain.wallet.dto.TransactionResponseDto;
import com.innovationcamp.messenger.domain.wallet.entity.GroupWalletUser;
import com.innovationcamp.messenger.domain.wallet.entity.PersonalWallet;
import com.innovationcamp.messenger.domain.wallet.entity.Transaction;
import com.innovationcamp.messenger.domain.wallet.repository.GroupWalletUserRepository;
import com.innovationcamp.messenger.domain.wallet.repository.PersonalWalletRepository;
import com.innovationcamp.messenger.domain.wallet.repository.TransactionRepository;
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
    private GroupWalletUserRepository groupWalletUserRepository;

    //추천인 받고 들어오면 시작머니 있다던가
    private final Long money = 0L;

    private PersonalWallet findPersonalWalletByUser(User user) {
        return personalWalletRepository.findByUser(user).orElseThrow(() -> new IllegalArgumentException("Wallet 을 개설하지 않은 유저입니다."));
    }

    public PersonalWallet getPersonalWallet(User user) {
        return findPersonalWalletByUser(user);
    }

    public PersonalWallet createPersonalWallet(User user, PersonalWalletCreateDto requestDto) {
        PersonalWallet personalWallet = new PersonalWallet(money, walletPasswordEncoder.encode(requestDto.getPassword()), user);
        return personalWalletRepository.save(personalWallet);
    }

    public PersonalWallet deletePersonalWallet(User user) {
        PersonalWallet personalWallet = findPersonalWalletByUser(user);
        personalWalletRepository.delete(personalWallet);
        return personalWallet;
    }

    public List<TransactionResponseDto> getAllTransactionByPersonalWallet(User user) {
        PersonalWallet personalWallet = findPersonalWalletByUser(user);
        List<Transaction> transactionList = transactionRepository.findAllByWallet(personalWallet);
        return transactionList.stream().map(TransactionResponseDto::new).collect(Collectors.toList());
    }

    public List<GroupWalletResponseDto> getAllGroupWalletByUser(User user) {
        List<GroupWalletUser> groupWalletUserList = groupWalletUserRepository.findAllByUser(user);
        return groupWalletUserList.stream().map(w -> new GroupWalletResponseDto(w.getGroupWallet())).collect(Collectors.toList());
    }
}
