package com.example.passfashion.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "voucher_order")
public class VoucherOrder {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonProperty("discount")
  private int discount;
  @JsonProperty("description")
  private String description;
  @JsonProperty("title")
  private String title;

  @Override
  public String toString() {
    return "VoucherOrder{" +
        "id=" + id +
        ", discount=" + discount +
        ", description='" + description + '\'' +
        ", title='" + title + '\'' +
        '}';
  }

}