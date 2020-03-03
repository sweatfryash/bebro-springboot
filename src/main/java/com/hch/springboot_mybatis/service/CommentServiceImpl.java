package com.hch.springboot_mybatis.service;

import com.hch.springboot_mybatis.entity.Comment;
import com.hch.springboot_mybatis.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("commentService")
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;

    @Autowired
    public CommentServiceImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }


    @Override
    public Integer likeComment(Integer userId, Integer commentId) {
        return commentMapper.likeComment(userId,commentId);
    }

    @Override
    public Integer cancelLikeComment(Integer userId, Integer commentId) {
        return commentMapper.cancelLikeComment(userId, commentId);
    }

    @Override
    public Integer addComment(Comment comment) {
        return null;
    }

    @Override
    public Integer deleteComment(Integer commentId) {
        return null;
    }
}
