package com.example.passfashion.dto.Response;
import com.example.passfashion.model.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;



@NoArgsConstructor
public class CreditCardResponse {
    private Long id;
    private String number;
    private String ownerName;
    private Date expiryDate;
    private String ccv;

    public CreditCardResponse(Long id, String number, String ownerName, Date expiryDate, String ccv) {
        this.id = id;
        this.number = number;
        this.ownerName = ownerName;
        this.expiryDate = expiryDate;
        this.ccv = ccv;
    }

    public String getCcv() {
        return ccv;
    }

    public void setCcv(String ccv) {
        this.ccv = ccv;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
