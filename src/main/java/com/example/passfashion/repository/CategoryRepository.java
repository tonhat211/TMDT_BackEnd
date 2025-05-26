package com.example.passfashion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.passfashion.dto.CategoryCountResponse;
import com.example.passfashion.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("""
            SELECT new com.example.passfashion.dto.CategoryCountResponse(
                p.category.link, count(*)) from Product p where p.isDeleted=0 AND p.isSold=0 GROUP by p.category.link
            """)
    List<CategoryCountResponse> countCategory();

}

//
