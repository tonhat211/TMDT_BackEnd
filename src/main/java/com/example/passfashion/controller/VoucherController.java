package com.example.passfashion.controller;

import com.example.passfashion.dto.Request.VoucherRequest;
import com.example.passfashion.service.VoucherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/vouchers")
public class VoucherController {
    @Autowired
    private VoucherService voucherService;
    @GetMapping
    public ResponseEntity<?> getAllVouchers() {
        try {
            return ResponseEntity.ok(voucherService.getAllVouchers());
        } catch (Exception e) {
            System.err.println("Error fetch vouchers: " + e.getMessage());
            return ResponseEntity.status(500).body("Error fetch vouchers: " + e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> addVouchers(@Valid @RequestBody VoucherRequest request) {
        try {
            voucherService.addVoucher(request);
            return ResponseEntity.ok("Create voucher successfully!");
        } catch (Exception e) {
            System.err.println("Error create vouchers: " + e.getMessage());
            return ResponseEntity.status(500).body("Error create vouchers: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{voucherId}")
    public ResponseEntity<?> deleteVoucher(@PathVariable long voucherId) {
        try {
            voucherService.deleteVoucher(voucherId);
            return ResponseEntity.ok("Delete voucher successfully!");
        } catch (Exception e) {
            System.err.println("Error delete voucher: " + e.getMessage());
            return ResponseEntity.status(500).body("Error delete voucher: " + e.getMessage());
        }
    }

    @PutMapping("/update/{voucherId}")
    public ResponseEntity<?> updateVoucher(@PathVariable long voucherId,@Valid @RequestBody VoucherRequest request) {
        try {
            voucherService.updateVoucher(voucherId,request);
            return ResponseEntity.ok("Update voucher successfully!");
        } catch (Exception e) {
            System.err.println("Error update voucher: " + e.getMessage());
            return ResponseEntity.status(500).body("Error update voucher: " + e.getMessage());
        }
    }

    @GetMapping("/find/{voucherCode}")
    public ResponseEntity<?> getVoucherByCode(@PathVariable String voucherCode) {
        try {
            return ResponseEntity.ok(voucherService.getVoucherByCode(voucherCode));
        } catch (Exception e) {
            System.err.println("Error delete voucher: " + e.getMessage());
            return ResponseEntity.status(500).body("Error delete voucher: " + e.getMessage());
        }
    }


}
