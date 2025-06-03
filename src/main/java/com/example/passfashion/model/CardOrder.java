package com.example.passfashion.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity

public class CardOrder {
  @Id
  @GeneratedValue
  private Long id;

  private String cardNumber;
  private String ownerName;
  private String type;
  private LocalDate expirationDate;
}