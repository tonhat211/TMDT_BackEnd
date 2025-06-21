package com.example.passfashion.service;

import com.example.passfashion.dto.Request.VoucherRequest;
import com.example.passfashion.model.Voucher;
import com.example.passfashion.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoucherService {
    @Autowired
    VoucherRepository voucherRepository;

    public List<Voucher> getAllVouchers() {
        return voucherRepository.findAll();
    }

    public void addVoucher(VoucherRequest request) {
        Voucher voucher = new Voucher();
        voucher.setCode(request.getCode());
        voucher.setDescription(request.getDescription());
        voucher.setDiscount(request.getDiscount());
        voucher.setQuantity(request.getQuantity());
        voucher.setMinOrderValue(request.getMinOrderValue());
        voucherRepository.save(voucher);
    }

    public void deleteVoucher(long voucherId) {
        Voucher voucher = voucherRepository.findById(voucherId).orElseThrow();
        voucher.setActive(false);
        voucherRepository.save(voucher);
    }

    public void updateVoucher(long voucherId,VoucherRequest request) {
        Voucher voucher = voucherRepository.findById(voucherId).orElseThrow();
        if(request.getCode()!=null){
            voucher.setCode(request.getCode());
        } else if (request.getDescription()!=null) {
            voucher.setDescription(request.getDescription());
        } else if (request.getQuantity()!=0) {
            voucher.setQuantity(request.getQuantity());
        } else if (request.getMinOrderValue()!=0) {
            voucher.setMinOrderValue(request.getMinOrderValue());
        } else if (request.getDiscount()!=0) {
            voucher.setDiscount(request.getDiscount());
        }
        voucherRepository.save(voucher);
    }

    public Voucher getVoucherByCode(String voucherCode) {
        return voucherRepository.findDistinctByCode(voucherCode);
    }
}
