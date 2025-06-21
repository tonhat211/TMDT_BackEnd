package com.example.passfashion.dto.Response;

public class VerifyCodeResponse {
    private boolean success;

    public VerifyCodeResponse(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
