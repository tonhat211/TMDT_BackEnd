package com.example.passfashion.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String email;
  private String phone;
  private Double total;
  private String status;
  // Map đến User

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  @JsonIgnore
  private User user;

  @OneToOne
  @JoinColumn(name = "address_order_id", nullable = false)
  private AddressOrder addressOrder;

  @OneToOne
  @JoinColumn(name = "card_order_id", nullable = false)
  private CardOrder cardOrder;

  @OneToOne
  @JoinColumn(name = "product_order_id", nullable = false)
  private ProductOrder productOrder;

  @OneToOne
  @JoinColumn(name = "voucher_order_id", nullable = false)
  private VoucherOrder voucherOrder;

  // Getters and Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
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

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
