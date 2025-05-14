package com.example.passfashion.controller;

import com.example.passfashion.dto.CategoryCountResponse;
import com.example.passfashion.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("/count")
    public ResponseEntity<List<CategoryCountResponse>> countCategory() {
        System.out.println("/categories/count");
        List<CategoryCountResponse> res = categoryRepository.countCategory();
        return ResponseEntity.ok(res);
    }


}

