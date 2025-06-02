package com.example.passfashion.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.passfashion.dto.Response.WishlistRespone;
import com.example.passfashion.model.Product;
import com.example.passfashion.service.WishlistService;

@RestController
@RequestMapping("api/v1/wishlists")
public class WishlistController {

  @Autowired
  private WishlistService wishlistService;

  // GET /api/v1/wishlists/{userId}
  @GetMapping("/{userId}")
  public List<WishlistRespone> getWishlistByUserId(@PathVariable Long userId) {
    // get all wishlist product from userID
    List<Product> wishlist = wishlistService.getWishlistByUserId(userId);

    return wishlist.stream().map(WishlistRespone::new).toList();
  }

  // POST /api/v1/wishlists/{userId}/add/{productId}
  @PostMapping("/{userId}/add/{productId}")
  public ResponseEntity<String> addProductToWishlist(
      @PathVariable Long userId,
      @PathVariable Long productId) {

    Optional<String> result = wishlistService.addProductToWishlist(userId, productId);

    if (result.isEmpty()) {
      return ResponseEntity.ok("Product added to wishlist successfully");
    } else {
      return ResponseEntity.badRequest().body(result.get());
    }
  }

}
