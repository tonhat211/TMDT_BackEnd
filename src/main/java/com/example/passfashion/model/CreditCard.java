package com.example.passfashion.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Pattern(regexp = "^(970\\d{1})\\d{11,16}$", message = "Số thẻ không hợp lệ. Phải bắt đầu bằng 970x và có 16 đến 19 chữ số.")
    private String number;
    private String ownerName;
    @Pattern(regexp = "\\d{3,4}", message = "CCV chỉ gồm 3 đến 4 chữ số")
    private String ccv;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @DateTimeFormat(pattern = "MM/yy")
    @Temporal(TemporalType.DATE)
    private Date expiryDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public @Pattern(regexp = "\\d{3,4}", message = "CCV chỉ gồm 3 đến 4 chữ số") String getCcv() {
        return ccv;
    }

    public void setCcv(@Pattern(regexp = "\\d{3,4}", message = "CCV chỉ gồm 3 đến 4 chữ số") String ccv) {
        this.ccv = ccv;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
