package com.example.passfashion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.passfashion.model.ProductOrder;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
  // Additional query methods can be defined here if needed
  /**
   * Save product order to database
   * 
   * @param productOrder
   * @return saved product order
   */
  ProductOrder save(ProductOrder productOrder);
}
