package com.example.passfashion.dto.Request;

import com.example.passfashion.model.AddressOrder;
import com.example.passfashion.model.CardOrder;
import com.example.passfashion.model.ProductOrder;
import com.example.passfashion.model.VoucherOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
  private Long idUser;
  private String email;
  private String phone;
  private Double total;
  private AddressOrder addressOrder;
  private CardOrder cardOrder;
  private ProductOrder productOrder;
  private VoucherOrder voucherOrder;

  // Add getters and setters

}
