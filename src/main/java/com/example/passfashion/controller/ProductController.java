package com.example.passfashion.controller;

import com.example.passfashion.dto.BasicProductResponse;
import com.example.passfashion.dto.CommentResponse;
import com.example.passfashion.dto.ProductDetailResponse;
import com.example.passfashion.model.*;
import com.example.passfashion.repository.CommentRepository;
import com.example.passfashion.repository.ProductRepository;
import com.example.passfashion.service.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CommentRepository commentRepository;

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
        Page<Product> productPage = productRepository.findByCategory(categoryCode, pageable);
        Page<BasicProductResponse> result = productPage.map(product ->
                new BasicProductResponse(
                        product.getId(),
                        product.getName(),
                        product.getPrice(),
                        product.getImages(),
                        product.getIsSold()
                )
        );
        return ResponseEntity.ok(result);
    }

    @PostMapping("/detail/{id}")
    public ResponseEntity<ProductDetailResponse> findById(
            @PathVariable long id) {
        System.out.println("/product/detail/"+id);
        Product product = productRepository.findById(id).orElse(null);
        Category category = product.getCategory();
        User owner = product.getUser();
        List<Image> images = product.getImages();
        List<CommentResponse> comments = findCommentByProductId(id);
        ProductDetailResponse result = new ProductDetailResponse(product,category,images,owner,comments);
        return ResponseEntity.ok(result);
    }

    public List<CommentResponse> findCommentByProductId(
            @PathVariable long id) {
        System.out.println("/product/comment/"+id);
        List<CommentResponse> commentResponses = commentRepository.findByProduct_Id(id);
        List<CommentResponse> result = new ArrayList<>();
        Map<Long,CommentResponse> map = new LinkedHashMap<>();
        for(CommentResponse comment : commentResponses){
            if(comment.getParentId()==0) {
                map.put(comment.getId(),comment);
            } else {
                map.get(comment.getParentId()).addReply(comment);
            }
        }
        for (CommentResponse comment : map.values()) {
            result.add(comment);
        }
        return result;
    }


}

