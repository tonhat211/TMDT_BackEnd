package com.example.passfashion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.passfashion.model.Product;
import com.example.passfashion.service.WishlistService;

@RestController
@RequestMapping("api/v1/wishlists")
public class WishlistController {

  @Autowired
  private WishlistService wishlistService;

  // GET /api/v1/wishlists/{userId}
  @GetMapping("/{userId}")
  public List<Product> getWishlistByUserId(@PathVariable Long userId) {
    return this.wishlistService.getWishlistByUserId(userId);
  }

  // POST /api/v1/wishlists/{userId}/add/{productId}
  @PostMapping("/{userId}/add/{productId}")
  public ResponseEntity<String> addProductToWishlist(
      @PathVariable Long userId,
      @PathVariable Long productId) {
    return wishlistService.addProductToWishlist(userId, productId)
        .map(product -> ResponseEntity.ok("Product added to wishlist successfully"))
        .orElse(ResponseEntity.badRequest().body("Failed to add product to wishlist"));
  }
}
