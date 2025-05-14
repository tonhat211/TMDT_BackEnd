package com.example.passfashion.controller;

import com.example.passfashion.dto.BasicProductResponse;
import com.example.passfashion.repository.ProductRepository;
import com.example.passfashion.service.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    //localhost:8080/api/v1/product/tui-xach?sortBy=price&direction=asc&page=0&size=10
    @PostMapping("/{categoryCode}")
    public ResponseEntity<Page<BasicProductResponse>> findByCategory(
            @PathVariable String categoryCode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = Constant.PAGE_SIZE_STRING) int size,
            @RequestParam(defaultValue = "price") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {

        System.out.println("/product/"+categoryCode+"\tpage: " + page);
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<BasicProductResponse> result = productRepository.findByCategory(categoryCode, pageable);
        return ResponseEntity.ok(result);
    }


}

