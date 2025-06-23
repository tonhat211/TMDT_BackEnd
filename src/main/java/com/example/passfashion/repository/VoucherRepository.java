package com.example.passfashion.repository;

import com.example.passfashion.model.Voucher;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoucherRepository extends JpaRepository<Voucher, Long> {

    Optional<Object> findByCode(String code);
}
