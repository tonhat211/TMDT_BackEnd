package com.example.passfashion.controller;

import com.example.passfashion.dto.Response.CategoryCountResponse;
import com.example.passfashion.model.Category;
import com.example.passfashion.repository.CategoryRepository;
import com.example.passfashion.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/count")
    public ResponseEntity<List<CategoryCountResponse>> countCategory() {
        System.out.println("/categories/count");
        List<CategoryCountResponse> res = categoryRepository.countCategory();
        return ResponseEntity.ok(res);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

}
