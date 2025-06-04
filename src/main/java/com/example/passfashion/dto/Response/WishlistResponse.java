package com.example.passfashion.dto.Response;

import java.util.ArrayList;
import java.util.List;

import com.example.passfashion.model.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishlistResponse {
  private long id;
  private String name;
  private double price;
  private String categoryName;
  private String imageUrl;
  private int qty;
  private String description;
  private List<String> imageUrls = new ArrayList<>();

  // constructor for map to wishlist response from product
  public WishlistResponse(Product product) {
    this.id = product.getId();
    this.name = product.getName();
    this.price = product.getPrice();
    this.categoryName = product.getCategory().getLink();
    this.imageUrl = product.getImages().isEmpty() ? null : product.getImages().get(0).getUrl();
    this.qty = product.getQty();
    this.description = product.getDescription();

    // Map danh sách URL ảnh
    // product.getImages().forEach(image -> this.imageUrls.add(image.getUrl()));
  }

}
