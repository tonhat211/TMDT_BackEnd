package com.example.passfashion.utils;

import java.time.LocalDateTime;

public class VerificationCode {
    private final String code;
    private final LocalDateTime expiryTime;

    public VerificationCode(String code, LocalDateTime expiryTime) {
        this.code = code;
        this.expiryTime = expiryTime;
    }

    public String getCode() {
        return code;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiryTime);
    }
}