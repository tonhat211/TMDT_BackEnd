package com.example.passfashion.repository;

import com.example.passfashion.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.passfashion.model.Order;
import com.example.passfashion.model.WishList;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
  /**
   * save order to database
   * 
   * @Param order
   * @return saved order
   */
  Order save(Order order);

    List<Order> findAllByUser(User user);

    List<Order> findAllByProductOrderId(long id);

    List<Order> findAllByUserId(long userId);
}
