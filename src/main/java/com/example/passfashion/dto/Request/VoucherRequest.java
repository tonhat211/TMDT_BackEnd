package com.example.passfashion.dto.Request;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoucherRequest {
    private int discount;
    private String description;
    @Size(min = 3,message ="Voucher code must longer than 3!" )
    private String code;
    private int quantity;
        private int minOrderValue;

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public @Size(min = 3, message = "Voucher code must longer than 3!") String getCode() {
        return code;
    }

    public void setCode(@Size(min = 3, message = "Voucher code must longer than 3!") String code) {
        this.code = code;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getMinOrderValue() {
        return minOrderValue;
    }

    public void setMinOrderValue(int minOrderValue) {
        this.minOrderValue = minOrderValue;
    }
}

