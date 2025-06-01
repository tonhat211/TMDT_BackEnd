package com.example.passfashion.repository;

import com.example.passfashion.dto.BasicProductResponse;
import com.example.passfashion.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("""
    SELECT p FROM Product p
    WHERE p.isDeleted = 0 
    AND p.isSold = 0
    AND p.category.link = :categoryLink
    """)
    Page<Product> findByCategory(@Param("categoryLink") String categoryLink, Pageable pageable);

    Optional<Product> findById(Long id);

    @Query("""
    SELECT p FROM Product p
    WHERE p.isDeleted = 0 
    AND p.isSold = 0
    """)
    Page<Product> findNewest(Pageable pageable);


}

//



