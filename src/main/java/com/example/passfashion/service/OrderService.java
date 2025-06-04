package com.example.passfashion.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.passfashion.dto.Request.OrderRequest;
import com.example.passfashion.model.*;
import com.example.passfashion.repository.*;

@Service
public class OrderService {

  @Autowired
  private OrderRepository orderRepository;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private AddressOrderRepository addressOrderRepository;
  @Autowired
  private CardOrderRepository cardOrderRepository;
  @Autowired
  private ProductOrderRepository productOrderRepository;
  @Autowired
  private VoucherOrderRepository voucherOrderRepository;

  public Optional<Order> createOrder(OrderRequest request) {
    User user = userRepository.findById(request.getIdUser())
        .orElseThrow(() -> new RuntimeException("User not found"));

    // 1. Lưu các entity con trước
    AddressOrder address = addressOrderRepository.save(request.getAddressOrder());
    CardOrder card = cardOrderRepository.save(request.getCardOrder());
    ProductOrder product = productOrderRepository.save(request.getProductOrder());
    VoucherOrder voucher = voucherOrderRepository.save(request.getVoucherOrder());

    // 2. Tạo order chính với các reference đã lưu
    Order order = new Order();
    // set các trường khác...
    order.setUser(user);
    order.setEmail(request.getEmail());
    order.setPhone(request.getPhone());
    order.setTotal(request.getTotal());

    order.setAddressOrder(address);
    order.setCardOrder(card);
    order.setProductOrder(product);
    order.setVoucherOrder(voucher);

    return Optional.of(orderRepository.save(order));
  }
}
