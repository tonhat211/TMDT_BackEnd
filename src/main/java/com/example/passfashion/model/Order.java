package com.example.passfashion.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
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

  // Map đến User
  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  // Map đến AddressOrder
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "address_order_id")
  private AddressOrder addressOrder;

  // Map đến CardOrder
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "card_order_id")
  private CardOrder cardOrder;

  // Map đến ProductOrder
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "product_order_id")
  private ProductOrder productOrder;

  // Map đến VoucherOrder
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "voucher_order_id")
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

}
