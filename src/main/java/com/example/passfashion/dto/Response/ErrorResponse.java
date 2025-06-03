package com.example.passfashion.dto.Response;

import lombok.Data;

@Data
public class ErrorResponse {
    private String message;
    private int status;
    private String timestamp;

    // getter and setter methods
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;

    }
}
