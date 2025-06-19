package com.example.passfashion.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.passfashion.model.Category;
import com.example.passfashion.repository.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll().stream()
                .filter(category -> category.getIsDeleted() == false)
                .collect(Collectors.toList());
    }
}
