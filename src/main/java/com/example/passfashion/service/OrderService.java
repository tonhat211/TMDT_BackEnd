package com.example.passfashion.service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import com.example.passfashion.dto.Request.OrderUpdateRequest;
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
  @Autowired
  ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

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
    order.setStatus("PENDING");
    order.setAddressOrder(address);
    order.setCardOrder(card);
    order.setProductOrder(product);
    order.setVoucherOrder(voucher);

    return Optional.of(orderRepository.save(order));
  }

  public List<Order> ordersBySeller(long userId) {
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
    List<Product> products = productRepository.findAllByUser(user);
    List<Order> orders = new ArrayList<>();
    for (Product product : products) {
        List<Order> list = orderRepository.findAllByProductOrderId(product.getId());
        orders.addAll(list);
    }
    return orders;
  }

  public List<Order> ordersUserBought(long userId) {
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
    return orderRepository.findAllByUserId(userId);
  }

  public List<SpendingDataPoint> getSpendingData(long userId) {
    List<Order> orders = ordersUserBought(userId);
    List<Category> categories = categoryRepository.findAll();
    List<SpendingDataPoint> data = new ArrayList<>();

    for (Category category : categories) {
      String label = category.getTitle();

      double total = orders.stream()
              .filter(order -> order.getProductOrder() != null &&
                      order.getProductOrder().getCategory() != null &&
                      order.getProductOrder().getCategory().getTitle().equalsIgnoreCase(label))
              .mapToDouble(Order::getTotal)
              .sum();

      data.add(new SpendingDataPoint(label, total));
    }

    return data;
  }

  public void updateStatus(long orderId, OrderUpdateRequest request) {
    Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new RuntimeException("Order not found"));
    if(request.getStatus()!=null){
      order.setStatus(request.getStatus());
    } else if (request.getEmail() != null) {
      order.setEmail(request.getEmail());
    } else if (request.getPhone() != null) {
      order.setPhone(request.getPhone());
    }
    orderRepository.save(order);
  }



  public record SpendingDataPoint(String label, double value) {
  }
}
