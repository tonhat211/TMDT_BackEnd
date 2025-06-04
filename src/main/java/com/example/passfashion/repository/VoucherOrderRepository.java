package com.example.passfashion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.passfashion.model.VoucherOrder;

public interface VoucherOrderRepository extends JpaRepository<VoucherOrder, Long> {

  /**
   * Save voucher order to database
   * 
   * @param voucherOrder
   * @return saved voucher order
   */
  VoucherOrder save(VoucherOrder voucherOrder);
}
