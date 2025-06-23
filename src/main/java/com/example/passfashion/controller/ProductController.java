package com.example.passfashion.controller;

import com.example.passfashion.dto.Request.PostProductRequest;
import com.example.passfashion.dto.Response.BasicProductResponse;
import com.example.passfashion.dto.Response.CommentResponse;
import com.example.passfashion.dto.Response.PostProductResponse;
import com.example.passfashion.dto.Response.ProductDetailResponse;
import com.example.passfashion.model.*;
import com.example.passfashion.repository.CommentRepository;
import com.example.passfashion.repository.ProductRepository;
import com.example.passfashion.security.JwtUtil;
import com.example.passfashion.service.CommentService;
import com.example.passfashion.service.Constant;
import com.example.passfashion.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ProductService productService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/create")
    public ResponseEntity<PostProductResponse> createProduct(@Valid @RequestBody PostProductRequest request,
            HttpServletRequest httpRequest) {
        // Lấy token từ header Authorization
        String authHeader = httpRequest.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new SecurityException("Token không hợp lệ hoặc thiếu");
        }

        String token = authHeader.substring(7);
        System.out.println("Token: " + token);
        Long authenticatedUserId = jwtUtil.getUserIdFromToken(token);
        System.out.println("User ID từ token: " + authenticatedUserId);
        System.out.println("User ID từ request: " + request.getUserId());
        if (authenticatedUserId == null) {
            throw new SecurityException("Không thể lấy userId từ token");
        }

        request.setUserId(authenticatedUserId);

        PostProductResponse response = productService.createProduct(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/material")
    public ResponseEntity<List<String>> getMaterialList() {
        return ResponseEntity.ok(productService.findDistinctMaterials());
    }

    // localhost:8080/api/v1/product/tui-xach?sortBy=price&direction=asc&page=0&size=10
    @PostMapping("/{categoryCode}")
    public ResponseEntity<Page<BasicProductResponse>> findByCategory(
            @PathVariable String categoryCode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = Constant.PAGE_SIZE_STRING) int size,
            @RequestParam(defaultValue = "price") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {

        System.out.println("/product/" + categoryCode + "\tpage: " + page);
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Product> productPage = productRepository.findByCategory(categoryCode, pageable);
        Page<BasicProductResponse> result = productPage.map(product -> new BasicProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getImages(),
                product.getIsSold()));
        return ResponseEntity.ok(result);
    }

    @PostMapping("/detail/{id}")
    public ResponseEntity<ProductDetailResponse> findById(
            @PathVariable long id) {
        System.out.println("/product/detail/" + id);
        Product product = productRepository.findByIdAdmin(id);
        Category category = product.getCategory();
        User owner = product.getUser();
        List<Image> images = product.getImages();
        List<CommentResponse> comments = commentService.findCommentByProductId(id);
        System.out.println("lay comments done");
        ProductDetailResponse result = new ProductDetailResponse(product, category, images, owner, comments);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/owner/{id}")
    public ResponseEntity<Page<BasicProductResponse>> findByUserId(
            @PathVariable long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = Constant.PAGE_SIZE_STRING) int size,
            @RequestParam(defaultValue = "price") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {

        System.out.println("/products/owner/" + id + "\tpage: " + page);
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Product> productPage = productRepository.findByUserId(id, pageable);
        Page<BasicProductResponse> result = productPage.map(product -> new BasicProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getImages(),
                product.getIsSold()));
        return ResponseEntity.ok(result);
    }

    @PostMapping("/search")
    public ResponseEntity<Page<BasicProductResponse>> findByName(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size,
            @RequestParam(defaultValue = "price") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {

        System.out.println("products/search?keyword=" + keyword);
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        String[] tokens = keyword.split(" ");
        ArrayList<String> keywords = new ArrayList<>();
        System.out.println("Tokens: ");
        for (String token : tokens) {
            keywords.add(token);
        }
        Page<Product> productPage = productService.searchByKeywords(keywords, pageable);
        for (Product product : productPage.getContent()) {
        }
        Page<BasicProductResponse> result = productPage.map(product -> new BasicProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getImages(),
                product.getIsSold()));
        return ResponseEntity.ok(result);
    }

    @PostMapping("/newest")
    public ResponseEntity<Page<BasicProductResponse>> findNewestProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = Constant.PAGE_SIZE_STRING) int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {

        System.out.println("/products/newest page: " + page);
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Product> productPage = productRepository.findNewest(pageable);
        Page<BasicProductResponse> result = productPage.map(product -> new BasicProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getImages(),
                product.getIsSold()));
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProductById(id);
            return ResponseEntity.ok("Xoá sản phẩm thành công");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/seller/{userId}")
    public ResponseEntity<?> getProductByUser(@PathVariable long userId) {
        try {
            return ResponseEntity.ok(productService.getProductByUser(userId));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/find/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable long productId) {
        try {
            return ResponseEntity.ok(productService.getProductById(productId));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable long productId, @RequestBody Product product) {
        try {
            productService.updateProduct(productId, product);
            return ResponseEntity.ok("Update product successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
