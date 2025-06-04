package com.example.passfashion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.passfashion.model.AddressOrder;

public interface AddressOrderRepository extends JpaRepository<AddressOrder, Long> {
  // Additional query methods can be defined here if needed
  /**
   * Save address order to database
   * 
   * @param addressOrder
   * @return saved address order
   */
  AddressOrder save(AddressOrder addressOrder);
}
