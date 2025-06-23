package com.example.passfashion.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
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
  @JsonProperty("id_pro")
  private Long idPro;
  @JsonProperty("price")
  private Double price;
  @JsonProperty("quantity")
  private Integer quantity;
  @JsonProperty("imageUrl")
  private String imageUrl;
  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

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

  public Category getCategory() {
    return category;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getIdPro() {
    return idPro;
  }

  public void setIdPro(Long idPro) {
    this.idPro = idPro;
  }

}