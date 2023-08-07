package com.innovationcamp.messenger.domain.wallet.entity;

public enum UserAuthorityEnum {
    USER(Authority.user),
    ADMIN(Authority.master);

    private final String authority;

    UserAuthorityEnum(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    public static class Authority {
        public static final String user = "USER";
        public static final String master = "MASTER";
    }
}
