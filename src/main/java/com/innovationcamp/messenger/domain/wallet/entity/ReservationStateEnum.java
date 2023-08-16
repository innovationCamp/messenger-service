package com.innovationcamp.messenger.domain.wallet.entity;

public enum ReservationStateEnum {
    RESERVATION(ReservationState.reservation),
    SUCCESS(ReservationState.success),
    FAILED(ReservationState.failed);


    private final String reservationState;

    ReservationStateEnum(String transferType) {
        this.reservationState = transferType;
    }

    public String ReservationState() {
        return this.reservationState;
    }

    public static class ReservationState {
        public static final String reservation = "RESERVATION";
        public static final String success = "SUCCESS";
        public static final String failed = "FAILED";
    }
}
