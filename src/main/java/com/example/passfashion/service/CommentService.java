package com.example.passfashion.service;

import com.example.passfashion.dto.CommentResponse;
import com.example.passfashion.model.Comment;
import com.example.passfashion.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<CommentResponse> findCommentByProductId(long id) {
        System.out.println("/product/comment/"+id);
        List<CommentResponse> commentResponses = commentRepository.findByProduct_Id(id);
        List<CommentResponse> result = new ArrayList<>();
        Map<Long,CommentResponse> map = new LinkedHashMap<>();
        for(CommentResponse comment : commentResponses){
            if(comment.getParentId()==0) {
                map.put(comment.getId(),comment);
            } else {
                if(map.get(comment.getParentId())!=null)
                map.get(comment.getParentId()).addReply(comment);
            }
        }
        for (CommentResponse comment : map.values()) {
            result.add(comment);
        }
        Collections.reverse(result);
        return result;
    }
}

