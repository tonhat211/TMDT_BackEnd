package com.example.passfashion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.passfashion.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
