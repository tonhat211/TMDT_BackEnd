package com.example.passfashion.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity

public class ProductOrder {
  @Id
  @GeneratedValue
  private Long id;
  private String name;
  private Double price;
  private Integer quantity;
  private String imageUrl;
}