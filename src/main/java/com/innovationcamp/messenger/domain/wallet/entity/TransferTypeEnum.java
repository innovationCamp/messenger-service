package com.innovationcamp.messenger.domain.wallet.entity;

public enum TransferTypeEnum {
    SEND(TransferType.send),
    RECEIVE(TransferType.receive);

    private final String transferType;

    TransferTypeEnum(String transferType) {
        this.transferType = transferType;
    }

    public String getTransferType() {
        return this.transferType;
    }

    public static class TransferType {
        public static final String send = "SEND";
        public static final String receive = "RECEIVE";
    }
}
