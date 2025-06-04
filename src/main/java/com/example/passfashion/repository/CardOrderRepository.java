package com.example.passfashion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.passfashion.model.CardOrder;

public interface CardOrderRepository extends JpaRepository<CardOrder, Long> {

  CardOrder save(CardOrder cardOrder);
}