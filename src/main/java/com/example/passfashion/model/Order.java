package com.example.passfashion.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String email;
  private String phone;
  private Double total;

  @OneToOne(cascade = CascadeType.ALL)
  private AddressOrder addressOrder;

  @OneToOne(cascade = CascadeType.ALL)
  private CardOrder cardOrder;

  @OneToOne(cascade = CascadeType.ALL)
  private ProductOrder productOrder;

  @OneToOne(cascade = CascadeType.ALL)
  private VoucherOrder voucherOrder;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
}