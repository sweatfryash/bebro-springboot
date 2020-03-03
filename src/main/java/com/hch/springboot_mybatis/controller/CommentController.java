package com.hch.springboot_mybatis.controller;

import com.hch.springboot_mybatis.service.CommentService;
import com.hch.springboot_mybatis.utils.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @RequestMapping("/likeComment")
    public JsonResult<Integer> likeComment(Integer userId ,Integer commentId){
        JsonResult<Integer> json;
        try {
            Integer res = commentService.likeComment(userId ,commentId);
            json = new JsonResult<>("1","点赞成功");
        }catch (Exception e){
            logger.warn(e.toString());
            json = new JsonResult<>("0","已经点赞过了");
        }
        return json;
    }
    @RequestMapping("/cancelLikeComment")
    public JsonResult<Integer> cancelLikeComment(Integer userId ,Integer commentId){
        JsonResult<Integer> json;
        Integer res = commentService.cancelLikeComment(userId ,commentId);
        json = new JsonResult<>(Integer.toString(res),"");
        return json;
    }
}
