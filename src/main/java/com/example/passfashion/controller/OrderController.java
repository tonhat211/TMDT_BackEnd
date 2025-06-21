package com.example.passfashion.controller;

import java.util.List;
import java.util.Optional;

import com.example.passfashion.dto.Request.OrderUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    return ResponseEntity.ok("Order creation process initiated");
  }
  @GetMapping("/sold-orders/{userId}")
  public ResponseEntity<?> getOrderBySeller(@PathVariable long userId) {
    try {
      return ResponseEntity.ok(orderService.ordersBySeller(userId));
    } catch (Exception e) {
      System.err.println("Error fetching orders: " + e.getMessage());
      return ResponseEntity.status(500).body("Failed to fetch orders: " + e.getMessage());
    }
  }


  @GetMapping("/bought-orders/{userId}")
  public ResponseEntity<?> getBoughtOrders(@PathVariable long userId) {
    try {
      return ResponseEntity.ok(orderService.ordersBySeller(userId));
    } catch (Exception e) {
      System.err.println("Error fetching orders: " + e.getMessage());
      return ResponseEntity.status(500).body("Failed to fetch orders: " + e.getMessage());
    }
  }

  @PutMapping("/update-status/{orderId}")
  public ResponseEntity<?> updateStatus(@PathVariable long orderId, @RequestBody OrderUpdateRequest request) {
    try {
      orderService.updateStatus(orderId, request); // Không trả về gì
      return ResponseEntity.ok("Cập nhật trạng thái thành công");
    } catch (Exception e) {
      System.err.println("Error update orders: " + e.getMessage());
      return ResponseEntity.status(500).body("Cập nhật thất bại: " + e.getMessage());
    }
  }
}