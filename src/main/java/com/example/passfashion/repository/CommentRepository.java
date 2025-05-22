package com.example.passfashion.repository;

import com.example.passfashion.model.Comment;
import com.example.passfashion.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByProduct_Id(Long id);

}

//



