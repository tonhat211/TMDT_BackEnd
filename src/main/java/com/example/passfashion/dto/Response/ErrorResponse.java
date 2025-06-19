package com.example.passfashion.dto.Response;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorResponse {
    private String message;
    private int status;
    private String timestamp;
    private Map<String, String> details;

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

    public Map<String, String> getDetails() {
        return this.details;
    }

    public void setDetails(final Map<String, String> details) {
        this.details = details;
    }

    /**
     * @param message
     * @param status
     * @param timestamp
     * @param details
     */
    public ErrorResponse(String message, int status, String timestamp, Map<String, String> details) {
        this.message = message;
        this.status = status;
        this.timestamp = timestamp;
        this.details = details;
    }

}
