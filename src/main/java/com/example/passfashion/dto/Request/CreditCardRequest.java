package com.example.passfashion.dto.Request;

import com.example.passfashion.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardRequest {
    @Pattern(regexp = "^(970\\d{1})\\d{11,16}$", message = "Số thẻ không hợp lệ. Phải bắt đầu bằng 970x và có 16 đến 19 chữ số.")
    private String number;
    private String ownerName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/yyyy")
    @Temporal(TemporalType.DATE)
    private Date expiryDate;

    @Pattern(regexp = "\\d{3,4}", message = "CCV chỉ gồm 3 đến 4 chữ số")
    private String ccv;
    private long userId;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public @Pattern(regexp = "\\d{3,4}", message = "CCV chỉ gồm 3 đến 4 chữ số") String getCcv() {
        return ccv;
    }

    public void setCcv(@Pattern(regexp = "\\d{3,4}", message = "CCV chỉ gồm 3 đến 4 chữ số") String ccv) {
        this.ccv = ccv;
    }
}
