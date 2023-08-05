package com.innovationcamp.messenger.domain.wallet.service;

import com.innovationcamp.messenger.domain.channel.entity.Channel;
import com.innovationcamp.messenger.domain.channel.repository.ChannelRepository;
import com.innovationcamp.messenger.domain.user.entity.User;
import com.innovationcamp.messenger.domain.wallet.config.WalletPasswordEncoder;
import com.innovationcamp.messenger.domain.wallet.dto.GroupWalletCreateDto;
import com.innovationcamp.messenger.domain.wallet.dto.GroupWalletResponseDto;
import com.innovationcamp.messenger.domain.wallet.dto.TransactionResponseDto;
import com.innovationcamp.messenger.domain.wallet.dto.WalletUserResponseDto;
import com.innovationcamp.messenger.domain.wallet.entity.AuthorityEnum;
import com.innovationcamp.messenger.domain.wallet.entity.GroupWallet;
import com.innovationcamp.messenger.domain.wallet.entity.GroupWalletUser;
import com.innovationcamp.messenger.domain.wallet.entity.Transaction;
import com.innovationcamp.messenger.domain.wallet.repository.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupWalletService {
    @NonNull
    private WalletPasswordEncoder walletPasswordEncoder;
    @NonNull
    private GroupWalletRepository groupWalletRepository;
    @NonNull
    private GroupWalletUserRepository groupWalletUserRepository;
    @NonNull
    private TransactionRepository transactionRepository;
    @NonNull
    private ChannelRepository channelRepository;

    private final Long money = 0L;

    private GroupWallet findGroupWalletById(Long groupWalletId) {
        return groupWalletRepository.findById(groupWalletId).orElseThrow(() -> new IllegalArgumentException("없는 Group Wallet 입니다."));
    }

    @Transactional
    public GroupWallet createGroupWallet(User user, GroupWalletCreateDto requestDto) {
        Channel channel = channelRepository.findById(requestDto.getChannelId()).orElseThrow(() -> new IllegalArgumentException("없는 채널입니다."));
        requestDto.setPassword(walletPasswordEncoder.encode(requestDto.getPassword()));
        GroupWallet groupWallet = GroupWallet.builder()
                .money(money)
                .channel(channel)
                .user(user)
                .requestDto(requestDto)
                .build();
        groupWallet = groupWalletRepository.save(groupWallet);

        GroupWalletUser groupWalletUser = GroupWalletUser.builder()
                .authority(AuthorityEnum.ADMIN)
                .user(user)
                .groupWallet(groupWallet)
                .build();
        groupWalletUserRepository.save(groupWalletUser);
        return groupWallet;
    }

    public GroupWallet getGroupWalletById(Long groupWalletId) {
        return findGroupWalletById(groupWalletId);
    }

    @Transactional
    public GroupWallet deleteGroupWalletById(Long groupWalletId) {
        GroupWallet groupWallet = findGroupWalletById(groupWalletId);
        groupWalletUserRepository.deleteByGroupWallet(groupWallet);
        groupWalletRepository.delete(groupWallet);
        return groupWallet;
    }

    public List<TransactionResponseDto> getTransactionByGroupWallet(Long groupWalletId) {
        GroupWallet groupWallet = findGroupWalletById(groupWalletId);
        List<Transaction> transactionList = transactionRepository.findAllByWallet(groupWallet);
        return transactionList.stream().map(TransactionResponseDto::new).collect(Collectors.toList());
    }

    public List<WalletUserResponseDto> getAllParticipantByGroupWallet(Long groupWalletId) {
        GroupWallet groupWallet = findGroupWalletById(groupWalletId);
        List<GroupWalletUser> groupWalletUserList = groupWalletUserRepository.findAllByGroupWallet(groupWallet);
        return groupWalletUserList.stream().map(w -> new WalletUserResponseDto(w.getUser(), w.getAuthority())).collect(Collectors.toList());
    }

    public GroupWallet participantGroupWalletById(User user, Long groupWalletId) {
        GroupWallet groupWallet = findGroupWalletById(groupWalletId);
        GroupWalletUser groupWalletUser = groupWalletUserRepository.findByUserAndGroupWallet(user, groupWallet).orElse(null);
        if (groupWalletUser != null) throw new IllegalArgumentException("이미 참여한 Group 통장입니다.");
        //일단 권한 USER
        groupWalletUser = GroupWalletUser.builder()
                .user(user)
                .groupWallet(groupWallet)
                .authority(AuthorityEnum.USER)
                .build();
        groupWalletUserRepository.save(groupWalletUser);
        return groupWallet;
    }

    public List<GroupWalletResponseDto> getAllGroupWalletByChannelId(Long channelId) {
        Channel channel = channelRepository.findById(channelId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 채널입니다."));
        return groupWalletRepository.findAllByChannel(channel).stream().map(GroupWalletResponseDto::new).collect(Collectors.toList());
    }
}
