package com.example.passfashion.controller;

import com.example.passfashion.dto.Response.AdminProductResponse;
import com.example.passfashion.dto.Response.BasicProductResponse;
import com.example.passfashion.dto.Response.CommentResponse;
import com.example.passfashion.dto.Response.ProductDetailResponse;
import com.example.passfashion.model.Category;
import com.example.passfashion.model.Image;
import com.example.passfashion.model.Product;
import com.example.passfashion.model.User;
import com.example.passfashion.repository.ProductRepository;
import com.example.passfashion.service.Constant;
import com.example.passfashion.service.NotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/ad/products")
public class AdminProductController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private NotifyService notifyService;

    @PostMapping("/all")
    public ResponseEntity<Page<AdminProductResponse>> findAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "1000") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {

        System.out.println("admin/products/all: " + page);
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Product> productPage = productRepository.findNewest(pageable);
        Page<AdminProductResponse> result = productPage.map(product -> new AdminProductResponse(
                product));
        return ResponseEntity.ok(result);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable long id) {
        System.out.println("admin/products/delete/id: " + id);
        Product product = productRepository.findByIdAdmin(id);
        product.setIsDeleted(true);
        Product saved = productRepository.save(product);

        Map<String, Object> re = new HashMap<>();
        boolean isSuccess =false;
        if(saved.getId()!=0) isSuccess = true;
        if(isSuccess) {
            re.put("id", id);
            long userId = product.getUser().getId();
            String name = product.getName();
            System.out.println("deleteProduct: userId: " + userId + ", name: " + name);
            String message ="Sản phẩm bị xóa do vi phạm chính sách: "+name +" với ID: "+ id;
            notifyService.notifyUser(userId, message);
        }
        re.put("success", isSuccess);

        return ResponseEntity.ok(re);

    }
}
