package com.example.passfashion.repository;

import com.example.passfashion.dto.BasicProductResponse;
import com.example.passfashion.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

   @Query("""
    SELECT new com.example.passfashion.dto.BasicProductResponse(
        p.id, p.name,p.price, p.thumbnail.url,p.isSold)
    FROM Product p
    WHERE p.isDeleted = 0 
    AND p.id = 0
    AND p.category.link = :categoryLink
    """)
   Page<BasicProductResponse> findByCategory(@Param("categoryLink") String categoryLink, Pageable pageable);

    @Query("""
    SELECT new com.example.passfashion.dto.BasicProductResponse(
        p.id, p.name,p.price, p.thumbnail.url,p.isSold)
    FROM Product p
    WHERE p.isDeleted = 0 
    AND p.isSold = 0
    ORDER BY p.createdAt DESC
    """)
    Page<BasicProductResponse> findSome(Pageable pageable);




}

//



