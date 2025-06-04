package com.example.passfashion.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.passfashion.dto.Request.OrderRequest;
import com.example.passfashion.model.Order;
import com.example.passfashion.service.OrderService;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {
  @Autowired
  OrderService orderService;

  @PostMapping()
  public ResponseEntity<String> createOrder(@RequestBody OrderRequest orderRequest) {

    // Thực hiện lưu vào DB nếu cần

    Optional<Order> check = orderService.createOrder(orderRequest);

    if (check.isEmpty()) {
      return ResponseEntity.badRequest().body("Failed to create order");
    }

    return ResponseEntity.ok("Order created successfully");
  }
}