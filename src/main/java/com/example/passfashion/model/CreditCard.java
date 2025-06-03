package com.example.passfashion.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Table(name = "credit_card")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private String ownerName;
    @Pattern(regexp = "\\d{3,4}", message = "CCV chỉ gồm 3 đến 4 chữ số")
    private String ccv;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @DateTimeFormat(pattern = "MM/yy")
    @Temporal(TemporalType.DATE)
    private Date expiryDate;





}
