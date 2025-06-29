package com.example.passfashion.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.passfashion.dto.Response.WishlistResponse;
import com.example.passfashion.model.Product;
import com.example.passfashion.service.WishlistService;

@RestController
@RequestMapping("api/v1/wishlists")
public class WishlistController {

  @Autowired
  private WishlistService wishlistService;

  // GET /api/v1/wishlists/{userId}
  @GetMapping("/{userId}")
  public ResponseEntity<List<WishlistResponse>> getWishlistByUserId(@PathVariable Long userId) {
    // get all wishlist product from userID
    List<Product> wishlist = wishlistService.getWishlistByUserId(userId);
    wishlist.forEach(product -> System.out.println("Product: " + (product == null ? "null" : product.getId())));

    List<WishlistResponse> wishlistResponses = wishlist.stream()
        .filter(product -> product != null && product.getId() != 0)
        .map(WishlistResponse::new)
        .toList();

    return ResponseEntity.ok(wishlistResponses);
  }

  // POST /api/v1/wishlists/{userId}/add/{productId}
  @PostMapping("/{userId}/add/{productId}")
  public ResponseEntity<String> addProductToWishlist(
      @PathVariable Long userId,
      @PathVariable Long productId) {
    try {

      Optional<String> result = wishlistService.addProductToWishlist(userId, productId);
      if (result.isEmpty()) {
        return ResponseEntity.ok("success");
      } else {
        return ResponseEntity.ok("failed");
      }

    } catch (Exception e) {
      return ResponseEntity.status(500).body("Internal server error");
    }

  }

  // delete /api/v1/wishlists/{userId}/remove/{productId}
  @DeleteMapping("/{userId}/remove/{productId}")
  public ResponseEntity<String> removeProductFromWishlist(
      @PathVariable Long userId,
      @PathVariable Long productId) {

    int result = wishlistService.deleteProductFromWishlist(userId, productId);

    System.out.println("result: " + result);
    if (result > 0) {
      return ResponseEntity.ok("Product removed from wishlist successfully");
    } else {
      return ResponseEntity.badRequest().body("Failed to remove product from wishlist");
    }
  }
}
