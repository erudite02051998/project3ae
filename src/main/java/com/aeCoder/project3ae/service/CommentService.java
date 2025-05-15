package com.aeCoder.project3ae.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aeCoder.project3ae.entity.Comment;
import com.aeCoder.project3ae.repository.CommentRepository;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByProject(String projectId) {
        return commentRepository.findByProjectId(projectId);
    }
}

