package com.example.passfashion.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.passfashion.dto.Request.CommentRequest;
import com.example.passfashion.dto.Response.CommentResponse;
import com.example.passfashion.model.Comment;
import com.example.passfashion.model.Product;
import com.example.passfashion.model.User;
import com.example.passfashion.repository.CommentRepository;
import com.example.passfashion.repository.ProductRepository;
import com.example.passfashion.repository.UserRepository;
import com.example.passfashion.service.CommentService;

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

    @PostMapping("/add")
    public ResponseEntity<List<CommentResponse>> addComment(@RequestBody CommentRequest request) {
        System.out.println("add comment: userId: " +request.getUserId());
        User user = userRepository.findById(request.getUserId()).get();
        Comment comment = new Comment();
        comment.setUser(user);
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
