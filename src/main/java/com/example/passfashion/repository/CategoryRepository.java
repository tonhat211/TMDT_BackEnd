package com.example.passfashion.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.passfashion.dto.Response.CategoryCountResponse;
import com.example.passfashion.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("""
            SELECT new com.example.passfashion.dto.Response.CategoryCountResponse(
                p.category.link, count(*))
            FROM Product p
            WHERE p.isDeleted = false AND p.isSold = false
            GROUP BY p.category.link
            """)
    List<CategoryCountResponse> countCategory();

    boolean existsByIdAndIsDeleted(Long id, boolean isDeleted);

    Optional<Category> findByTitleIgnoreCaseAndIsDeletedFalse(String title);
}

//
