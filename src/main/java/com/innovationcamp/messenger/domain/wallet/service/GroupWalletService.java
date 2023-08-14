package com.innovationcamp.messenger.domain.wallet.service;

import com.innovationcamp.messenger.domain.channel.entity.Channel;
import com.innovationcamp.messenger.domain.channel.repository.ChannelRepository;
import com.innovationcamp.messenger.domain.channel.repository.UserChannelRepository;
import com.innovationcamp.messenger.domain.user.entity.User;
import com.innovationcamp.messenger.domain.wallet.config.WalletPasswordEncoder;
import com.innovationcamp.messenger.domain.wallet.dto.GroupWalletCreateDto;
import com.innovationcamp.messenger.domain.wallet.dto.GroupWalletResponseDto;
import com.innovationcamp.messenger.domain.wallet.dto.TransactionResponseDto;
import com.innovationcamp.messenger.domain.wallet.dto.WalletUserResponseDto;
import com.innovationcamp.messenger.domain.wallet.entity.UserAuthorityEnum;
import com.innovationcamp.messenger.domain.wallet.entity.GroupWallet;
import com.innovationcamp.messenger.domain.wallet.entity.UserGroupWallet;
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
    private UserGroupWalletRepository userGroupWalletRepository;
    @NonNull
    private TransactionRepository transactionRepository;
    @NonNull
    private ChannelRepository channelRepository;
    @NonNull
    private UserChannelRepository userChannelRepository;

    private final Long money = 0L;

    private GroupWallet findGroupWalletById(Long groupWalletId) {
        return groupWalletRepository.findById(groupWalletId).orElseThrow(() -> new IllegalArgumentException("없는 Group Wallet 입니다."));
    }

    private void validateUserChannel(Long channelId, Long userId){
        if (!userChannelRepository.existsByChannelIdAndUserId(channelId, userId))
            throw new IllegalArgumentException("채널의 구성원이 아닙니다.");
    }

    @Transactional
    public GroupWallet createGroupWallet(User user, GroupWalletCreateDto requestDto) {
        Channel channel = channelRepository.findById(requestDto.getChannelId()).orElseThrow(() -> new IllegalArgumentException("없는 채널입니다."));
        validateUserChannel(requestDto.getChannelId(), user.getId());
        GroupWallet groupWallet = GroupWallet.builder()
                .money(money)
                .user(user)
                .channel(channel)
                .password(walletPasswordEncoder.encode(requestDto.getPassword()))
                .walletName(requestDto.getWalletName())
                .description(requestDto.getDescription())
                .build();
        groupWallet = groupWalletRepository.save(groupWallet);

        UserGroupWallet userGroupWallet = new UserGroupWallet(UserAuthorityEnum.ADMIN, user, groupWallet);
        userGroupWalletRepository.save(userGroupWallet);
        return groupWallet;
    }

    public GroupWallet getGroupWalletById(User user, Long groupWalletId) {
        validateUserChannel(groupWalletId, user.getId());
        return findGroupWalletById(groupWalletId);
    }

    @Transactional
    public GroupWallet deleteGroupWalletById(Long groupWalletId) {
        GroupWallet groupWallet = findGroupWalletById(groupWalletId);
        userGroupWalletRepository.deleteByGroupWallet(groupWallet);
        groupWalletRepository.delete(groupWallet);
        return groupWallet;
    }

    public List<TransactionResponseDto> getTransactionByGroupWallet(User user, Long groupWalletId) {
        if(!userGroupWalletRepository.existsByGroupWalletIdAndUserId(groupWalletId, user.getId())){
            throw new IllegalArgumentException("참가하지 않은 Group 통장입니다.");
        }
        GroupWallet groupWallet = findGroupWalletById(groupWalletId);
        List<Transaction> transactionList = transactionRepository.findAllByWallet(groupWallet);
        return transactionList.stream().map(TransactionResponseDto::new).collect(Collectors.toList());
    }

    public List<WalletUserResponseDto> getAllParticipantByGroupWallet(Long groupWalletId) {
        GroupWallet groupWallet = findGroupWalletById(groupWalletId);
        List<UserGroupWallet> userGroupWalletList = userGroupWalletRepository.findAllByGroupWallet(groupWallet);
        return userGroupWalletList.stream().map(w -> new WalletUserResponseDto(w.getUser(), w.getUserAuthority())).collect(Collectors.toList());
    }

    public GroupWallet participantGroupWalletById(User user, Long groupWalletId) {
        GroupWallet groupWallet = findGroupWalletById(groupWalletId);
        validateUserChannel(groupWallet.getChannel().getId(), user.getId());
        UserGroupWallet userGroupWallet = userGroupWalletRepository.findByUserAndGroupWallet(user, groupWallet).orElse(null);
        if (userGroupWallet != null) throw new IllegalArgumentException("이미 참여한 Group 통장입니다.");
        //일단 권한 USER
        userGroupWallet = new UserGroupWallet(UserAuthorityEnum.USER, user, groupWallet);
        userGroupWalletRepository.save(userGroupWallet);
        return groupWallet;
    }

    public List<GroupWalletResponseDto> getAllGroupWalletByChannelId(User user, Long channelId) {
        Channel channel = channelRepository.findById(channelId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 채널입니다."));
        validateUserChannel(channelId, user.getId());
        return groupWalletRepository.findAllByChannel(channel).stream().map(GroupWalletResponseDto::new).collect(Collectors.toList());
    }
}
