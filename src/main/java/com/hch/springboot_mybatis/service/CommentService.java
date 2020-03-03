package com.hch.springboot_mybatis.service;

import com.hch.springboot_mybatis.entity.Comment;

public interface CommentService {

    Integer likeComment(Integer userId ,Integer commentId);

    Integer cancelLikeComment(Integer userId ,Integer commentId);

    Integer addComment(Comment comment);

    Integer deleteComment(Integer commentId);
}
