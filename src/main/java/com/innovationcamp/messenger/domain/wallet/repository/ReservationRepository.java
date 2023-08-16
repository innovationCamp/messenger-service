package com.innovationcamp.messenger.domain.wallet.repository;

import com.innovationcamp.messenger.domain.wallet.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByReservationTimeBetween(LocalDateTime startTime, LocalDateTime endTime);
    List<Reservation> findAllByWalletId(Long id);
}
