package com.example.passfashion.dto.Response;
import com.example.passfashion.model.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardResponse {
    private Long id;
    private String number;
    private String ownerName;
    private Date expiryDate;
}
