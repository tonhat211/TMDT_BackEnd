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

public class VoucherOrder {
  @Id
  @GeneratedValue
  private Long id;
  private int discount;
  private String description;
  private String title;
  private LocalDate startDate;
}