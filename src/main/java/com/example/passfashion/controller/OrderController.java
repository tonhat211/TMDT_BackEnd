package com.example.passfashion.controller;

import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.passfashion.dto.Request.OrderRequest;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {
  @PostMapping()
  public ResponseEntity<String> createOrder(@RequestBody OrderRequest orderRequest) {
    System.out.println("Received order from: " + orderRequest);

    // Thực hiện lưu vào DB nếu cần
    return ResponseEntity.ok("Order created successfully");
  }
}