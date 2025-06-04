package com.example.passfashion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.passfashion.model.Order;
import com.example.passfashion.model.WishList;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
  /**
   * save order to database
   * 
   * @Param order
   * @return saved order
   */
  Order save(Order order);
}
