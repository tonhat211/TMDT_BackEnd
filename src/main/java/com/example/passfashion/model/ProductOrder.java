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
@Table(name = "product_order")
public class ProductOrder {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonProperty("name")
  private String name;
  @JsonProperty("price")
  private Double price;
  @JsonProperty("quantity")
  private Integer quantity;
  @JsonProperty("imageUrl")
  private String imageUrl;

  @Override
  public String toString() {
    return "ProductOrder{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", price=" + price +
        ", quantity=" + quantity +
        ", imageUrl='" + imageUrl + '\'' +
        '}';
  }

}