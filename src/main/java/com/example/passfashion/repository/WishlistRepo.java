
package com.example.passfashion.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.passfashion.model.Product;
import com.example.passfashion.model.WishList;

import jakarta.transaction.Transactional;

@Repository
public interface WishlistRepo extends JpaRepository<WishList, Long> {

  @Query("SELECT w.product FROM WishList w WHERE w.user.id = :userId")
  List<Product> findAllProductsByUserId(@Param("userId") Long userId);

  @Transactional
  @Modifying
  @Query(nativeQuery = true, value = """
      INSERT INTO wishlists (user_id, product_id, created_at)
      SELECT :userId, :productId, NOW()
      WHERE NOT EXISTS (
          SELECT 1 FROM wishlists
          WHERE user_id = :userId AND product_id = :productId
      )
      """)
  int insertIfNotExists(@Param("userId") Long userId, @Param("productId") Long productId);

  default Optional<String> addProductToWishlist(Long userId, Long productId) {
    int rowsAffected = insertIfNotExists(userId, productId);
    return rowsAffected > 0
        ? Optional.empty() // Thêm thành công -> không có lỗi
        : Optional.of("Product already exists in wishlist");
  }

  @Transactional
  @Modifying
  @Query("DELETE FROM WishList w WHERE w.user.id = :userId AND w.product.id = :productId")
  int deleteByUserIdAndProductId(@Param("userId") Long userId, @Param("productId") Long productId);
}
