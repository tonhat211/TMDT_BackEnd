package com.example.passfashion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.passfashion.model.VoucherOrder;
import com.nimbusds.openid.connect.sdk.assurance.evidences.Voucher;

public interface VoucherOrderRepository extends JpaRepository<VoucherOrder, Long> {

  /**
   * Save voucher order to database
   * 
   * @param voucherOrder
   * @return saved voucher order
   */
  VoucherOrder save(VoucherOrder voucherOrder);
}
