package com.innovationcamp.messenger.domain.wallet.service;

import com.innovationcamp.messenger.domain.wallet.dto.ReservationCreateDto;
import com.innovationcamp.messenger.domain.wallet.entity.Reservation;
import com.innovationcamp.messenger.domain.wallet.entity.ReservationStateEnum;
import com.innovationcamp.messenger.domain.wallet.repository.ReservationRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
    @NonNull
    private final WalletService walletService;
    @NonNull
    private final ReservationRepository reservationRepository;

    @Scheduled(cron = "0 0 10-14 * * ?", zone = "Asia/Seoul")
    public void reservation(){
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = startTime.plusHours(1);
        List<Reservation> reservations = reservationRepository.findAllByReservationTimeBetween(startTime, endTime);
        for (Reservation reservation : reservations) {
            if(walletService.reservationTransaction(reservation)){
                reservation.updateStatus(ReservationStateEnum.SUCCESS);
            }else {
                reservation.updateStatus(ReservationStateEnum.FAILED);
            }
            if (reservation.getReservationType().equals(ReservationCreateDto.ReservationType.MONTHLY)){
                LocalDateTime reservationTime = reservation.getReservationTime().plusMonths(1);
                Reservation nextReservation = Reservation.builder()
                        .amount(reservation.getAmount())
                        .reservationTime(reservationTime)
                        .reservationType(reservation.getReservationType())
                        .reservationState(ReservationStateEnum.RESERVATION)
                        .build();
            }
        }
    }
}
