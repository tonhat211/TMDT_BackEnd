package com.example.passfashion.repository;

import com.example.passfashion.dto.Response.CommentResponse;
import com.example.passfashion.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("""
            SELECT new com.example.passfashion.dto.Response.CommentResponse(
                c.id, c.product.id, c.user.id, c.user.name,c.user.avatar, c.content, c.createdAt,CASE WHEN c.parentComment IS NULL THEN 0 ELSE c.parentComment.id END,c.level) from Comment c
                where c.isDeleted=0
                AND c.product.id = :productId
                ORDER BY c.createdAt ASC
            """)
    List<CommentResponse> findByProduct_Id(@Param("productId") Long productId);

}

//
