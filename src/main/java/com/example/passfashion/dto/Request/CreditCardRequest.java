package com.example.passfashion.dto.Request;

import com.example.passfashion.model.User;
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
    private String number;
    private String ownerName;
    @DateTimeFormat(pattern = "MM/yy")
    @Temporal(TemporalType.DATE)
    private Date expiryDate;
    @Pattern(regexp = "\\d{3,4}", message = "CCV chỉ gồm 3 đến 4 chữ số")
    private String ccv;
    private long userId;
}
