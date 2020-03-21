package com.hch.springboot_mybatis.service;

import com.hch.springboot_mybatis.entity.Reply;

import java.util.List;

public interface  ReplyService {
    List<Reply> getReplyByCommentId(Integer askId, Integer commentId);
    Integer likeReply(Integer userId ,Integer replyId);
    Integer cancelLikeReply(Integer userId ,Integer replyId);
    Integer addReply(Reply reply);
    Integer deleteReply(Integer replyId);
}
