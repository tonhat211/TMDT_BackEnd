package com.example.passfashion.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    // System.out.println("Received order request: " + orderRequest.toString());

    try {

      Optional<Order> check = orderService.createOrder(orderRequest);
      if (check.isPresent()) {
        System.out.println("Order created successfully: " + check.get().toString());
        return ResponseEntity.ok("Order created successfully");
      }
    } catch (Exception e) {
      // Xử lý lỗi nếu có
      System.err.println("Error creating order: " + e.getMessage());
      return ResponseEntity.status(500).body("Failed to create order: " + e.getMessage());
    }

    // Kiểm tra xem đơn hàng đã được tạo thành công hay chưa
    // Uncomment the following lines if you want to return a response based on the
    // order creation status

    // if (check.isEmpty()) {
    // return ResponseEntity.ok("Failed to create order");
    // }
    return ResponseEntity.ok("Order creation process initiated");
    // return ResponseEntity.ok("Order created successfully");
  }
}