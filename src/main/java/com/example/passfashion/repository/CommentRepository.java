package com.example.passfashion.repository;

import com.example.passfashion.dto.CommentResponse;
import com.example.passfashion.model.Comment;
import com.example.passfashion.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("""
    SELECT new com.example.passfashion.dto.CommentResponse(
        c.id, c.product.id, c.user.id, c.user.name,c.user.image.url, c.content, c.createdAt,CASE WHEN c.parentComment IS NULL THEN 0 ELSE c.parentComment.id END,c.level) from Comment c 
        where c.isDeleted=0 
        AND c.product.id = :productId
        ORDER BY c.createdAt ASC
    """)
    List<CommentResponse> findByProduct_Id(@Param("productId") Long productId);

}

//



