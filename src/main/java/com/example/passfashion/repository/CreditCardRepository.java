package com.example.passfashion.repository;

import com.example.passfashion.dto.Response.CreditCardResponse;
import com.example.passfashion.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

    List<CreditCard> findAllByUserId(long userid);
}
