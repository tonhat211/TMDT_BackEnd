package com.example.passfashion.repository;

import com.example.passfashion.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoucherRepository extends JpaRepository<Voucher, Long> {
    Voucher findDistinctByCode(String voucherCode);
}
