package com.example.passfashion.controller;

import com.example.passfashion.dto.BasicProductResponse;
import com.example.passfashion.dto.CommentRequest;
import com.example.passfashion.dto.CommentResponse;
import com.example.passfashion.dto.ProductDetailResponse;
import com.example.passfashion.model.*;
import com.example.passfashion.repository.CommentRepository;
import com.example.passfashion.repository.ProductRepository;
import com.example.passfashion.repository.UserRepository;
import com.example.passfashion.service.CommentService;
import com.example.passfashion.service.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("api/v1/comments")
public class CommentController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<List<CommentResponse>> addComment(@RequestBody CommentRequest request) {
        System.out.println("add comment");
        Comment comment = new Comment();
        comment.setUser(new User(request.getUserId()));
        comment.setProduct(new Product(request.getProductId()));
        comment.setContent(request.getContent());
        comment.setLevel(request.getLevel());
        comment.setCreatedAt(LocalDateTime.now());
        comment.setIsDeleted(0);

        if (request.getParentId() > 0) {
            comment.setParentComment(new Comment(request.getParentId()));
        }

        Comment saved = commentRepository.save(comment);
        List<CommentResponse> result = commentService.findCommentByProductId(request.getProductId());
        return ResponseEntity.ok(result);
    }

    @PostMapping("/get")
    public ResponseEntity<List<CommentResponse>> getComments() {
        System.out.println("get comments");

        List<CommentResponse> result = commentService.findCommentByProductId(2);
        return ResponseEntity.ok(result);
    }


}

