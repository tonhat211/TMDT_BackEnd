package com.example.passfashion.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.passfashion.dto.Request.OrderRequest;
import com.example.passfashion.model.Order;
import com.example.passfashion.model.User;
import com.example.passfashion.repository.OrderRepository;
import com.example.passfashion.repository.UserRepository;

@Service
public class OrderService {

  @Autowired
  private OrderRepository orderRepository;
  @Autowired
  private UserRepository userRepository;

  public Optional<Order> createOrder(OrderRequest request) {
    User user = userRepository.findById(request.getIdUser())
        .orElseThrow(() -> new RuntimeException("User not found"));

    Order order = new Order();
    order.setEmail(request.getEmail());
    order.setPhone(request.getPhone());
    order.setTotal(request.getTotal());
    order.setUser(user);

    // Gắn các thành phần liên quan
    order.setAddressOrder(request.getAddressOrder());
    order.setCardOrder(request.getCardOrder());
    order.setProductOrder(request.getProductOrder());
    order.setVoucherOrder(request.getVoucherOrder());

    return Optional.of(orderRepository.save(order));
  }
}
