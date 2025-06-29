package com.example.passfashion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.passfashion.model.Product;
import com.example.passfashion.repository.WishlistRepo;

@Service
public class WishlistService {
  @Autowired
  private WishlistRepo wishlistRepo;

  /**
   * Retrieves the wishlist for a user by their ID.
   * 
   * @param userId the ID of the user
   * @return a list of products in the user's wishlist
   */
  public List<Product> getWishlistByUserId(Long userId) {
    // Call the repository method to find all products in the wishlist by user ID
    System.out.println("Fetching wishlist for user ID: " + userId);
    return wishlistRepo.findAllProductsByUserId(userId);
  }

  /**
   * adding product to wishlist
   * 
   * @param userId    the ID of the user
   * @param productId the ID of the product to add
   * @return an optional product if added successfully, otherwise empty
   */
  public Optional<String> addProductToWishlist(Long userId, Long productId) throws Exception {
    return wishlistRepo.addProductToWishlist(userId, productId);

  }

  /**
   * delete product from wishlist
   * 
   * @param userId    the ID of the user
   * @param productId the ID of the product to delete
   * @return an optional message indicating success or failure
   * 
   */
  public int deleteProductFromWishlist(Long userId, Long productId) {
    // Call the repository method to delete a product from the wishlist
    return wishlistRepo.deleteByUserIdAndProductId(userId, productId);

  }
}