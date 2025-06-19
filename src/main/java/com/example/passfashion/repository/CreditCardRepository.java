package com.example.passfashion.repository;

import com.example.passfashion.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {



    CreditCard findAllByUserId(long userid);
}
