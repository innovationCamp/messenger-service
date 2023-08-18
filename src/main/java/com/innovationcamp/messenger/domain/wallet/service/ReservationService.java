package com.innovationcamp.messenger.domain.wallet.service;

import com.innovationcamp.messenger.domain.wallet.dto.ReservationCreateDto;
import com.innovationcamp.messenger.domain.wallet.entity.Reservation;
import com.innovationcamp.messenger.domain.wallet.entity.ReservationStateEnum;
import com.innovationcamp.messenger.domain.wallet.repository.ReservationRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationService {
    @NonNull
    private final WalletService walletService;
    @NonNull
    private final ReservationRepository reservationRepository;

    // 1분 간격 실행
    @Scheduled(cron = "0 0/1 * * * ?", zone = "Asia/Seoul")
    @Transactional
    public void reservation(){
        LocalDateTime startTime = LocalDateTime.now().withSecond(0).withNano(0);
        // 1분 간격 조회
        LocalDateTime endTime = startTime.plusMinutes(1);

        log.info("예약송금 실행 : " + startTime + " ~ " + endTime);
        List<Reservation> reservations = reservationRepository.findAllByReservationTimeBetween(startTime, endTime); // 이상 ~ 이하
        for (Reservation reservation : reservations) {
            if (reservation.getReservationState().equals(ReservationStateEnum.RESERVATION)) {
                if (walletService.reservationTransaction(reservation)) {
                    reservation.updateState(ReservationStateEnum.SUCCESS);
                } else {
                    reservation.updateState(ReservationStateEnum.FAILED);
                }
            }
            if (reservation.getReservationType().equals(ReservationCreateDto.ReservationType.MONTHLY)){
                LocalDateTime reservationTime = reservation.getReservationTime().plusMonths(1);
                Reservation nextReservation = Reservation.builder()
                        .wallet(reservation.getWallet())
                        .targetWallet(reservation.getTargetWallet())
                        .amount(reservation.getAmount())
                        .reservationTime(reservationTime)
                        .reservationType(reservation.getReservationType())
                        .reservationState(ReservationStateEnum.RESERVATION)
                        .user(reservation.getUser())
                        .build();
                reservationRepository.save(nextReservation);
            }
        }
    }
}
