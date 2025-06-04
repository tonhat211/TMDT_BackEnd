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
  private Long idUser; // ID của user đặt hàng
  private String email; // Email người nhận
  private String phone; // Số điện thoại người nhận
  private Double total; // Tổng tiền đơn hàng

  private AddressOrder addressOrder; // Địa chỉ nhận hàng
  private CardOrder cardOrder; // Thông tin thẻ (nếu có)
  private ProductOrder productOrder; // Sản phẩm đã chọn
  private VoucherOrder voucherOrder;

  // getters and setters
  public Long getIdUser() {
    return idUser;
  }

  public void setIdUser(Long idUser) {
    this.idUser = idUser;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Double getTotal() {
    return total;
  }

  public void setTotal(Double total) {
    this.total = total;
  }

  public AddressOrder getAddressOrder() {
    return addressOrder;
  }

  public void setAddressOrder(AddressOrder addressOrder) {
    this.addressOrder = addressOrder;
  }

  public CardOrder getCardOrder() {
    return cardOrder;
  }

  public void setCardOrder(CardOrder cardOrder) {
    this.cardOrder = cardOrder;
  }

  public ProductOrder getProductOrder() {
    return productOrder;
  }

  public void setProductOrder(ProductOrder productOrder) {
    this.productOrder = productOrder;
  }

  public VoucherOrder getVoucherOrder() {
    return voucherOrder;
  }

  public void setVoucherOrder(VoucherOrder voucherOrder) {
    this.voucherOrder = voucherOrder;
  }

}
