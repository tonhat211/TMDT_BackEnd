package com.example.passfashion.repository;

import com.example.passfashion.dto.Response.AddressResponse;
import com.example.passfashion.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> getAddressesByUserId(Long userId);
}
