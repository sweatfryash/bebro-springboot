package com.hch.springboot_mybatis.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hch.springboot_mybatis.entity.Reply;
import com.hch.springboot_mybatis.service.ReplyService;
import com.hch.springboot_mybatis.utils.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reply")
public class ReplyController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private final ReplyService replyService;

    @Autowired
    public ReplyController(ReplyService replyService){
        this.replyService = replyService;
    }

    @RequestMapping("/likeReply")
    public JsonResult<Integer> likeReply(Integer userId , Integer replyId){
        JsonResult<Integer> json;
        try {
            Integer res = replyService.likeReply(userId ,replyId);
            json = new JsonResult<>("1","点赞成功");
        }catch (Exception e){
            logger.warn(e.toString());
            json = new JsonResult<>("0","已经点赞过了");
        }
        return json;
    }
    @RequestMapping("/cancelLikeReply")
    public JsonResult<Integer> cancelLikeReply(Integer userId ,Integer replyId){
        JsonResult<Integer> json;
        Integer res = replyService.cancelLikeReply(userId ,replyId);
        json = new JsonResult<>(Integer.toString(res),"");
        return json;
    }
    @RequestMapping("/addReply")
    public JsonResult<Integer> addReply(Reply reply){
        JsonResult<Integer> json;
        try {
            Integer res = replyService.addReply(reply);
            json = new JsonResult<>("1","回复成功");
        }catch (Exception e){
            logger.warn(e.toString());
            json = new JsonResult<>("0","回复失败，原动态可能已被删除");
        }
        return json;
    }
    @RequestMapping("/deleteReply")
    public JsonResult<Integer> deleteReply(Integer replyId){
        JsonResult<Integer> json;
        try {
            Integer res = replyService.deleteReply(replyId);
            json = new JsonResult<>("1","回复已删除");
        }catch (Exception e){
            logger.warn(e.toString());
            json = new JsonResult<>("0","删除回复失败");
        }
        return json;
    }
    @RequestMapping("/getReplyByCommentId")
    public JsonResult<List<Reply>> getReplyByCommentId(Integer askId, Integer commentId, Integer page){
        PageHelper.startPage(page, 10);
        List<Reply> res = replyService.getReplyByCommentId(askId, commentId);
        PageInfo<Reply> pageInfo = new PageInfo<Reply>(res);
        JsonResult<List<Reply>> json;
        json = !res.isEmpty()
                ? new JsonResult<List<Reply>>(res, "1", "获取回复",pageInfo.getPages())
                : new JsonResult<List<Reply>>(null, "0", "回复数量为0",pageInfo.getPages());
        return json;
    }
}
