package com.hch.springboot_mybatis.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hch.springboot_mybatis.entity.Comment;
import com.hch.springboot_mybatis.entity.Post;
import com.hch.springboot_mybatis.service.PostService;
import com.hch.springboot_mybatis.utils.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@RequestMapping("/post")
public class PostController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private final PostService postService;

    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }
    @RequestMapping("/getPostsById")
    public JsonResult<List<Post> > getPostsById(Integer askId,Integer userId,Integer page) {
        PageHelper.startPage(page, 10);
        List<Post> res = postService.getPostsById(askId, userId);
        PageInfo<Post> pageInfo = new PageInfo<Post>(res);
        JsonResult<List<Post> > json;
        json = !res.isEmpty()
                ? new JsonResult<List<Post> >(res, "1", "获取到动态",pageInfo.getPages())
                : new JsonResult<List<Post> >(null, "0", "动态数量为0",pageInfo.getPages());
        return json;
    }
    @RequestMapping("/getAllPostsByDate")
    public JsonResult<List<Post> > getAllPostsByDate(Integer userId,Integer page) {
        PageHelper.startPage(page, 10);
        List<Post> res = postService.getAllPostsByDate(userId);
        PageInfo<Post> pageInfo = new PageInfo<Post>(res);
        JsonResult<List<Post> > json;
        json = !res.isEmpty()
                ? new JsonResult<List<Post> >(res, "1", "获取到动态",pageInfo.getPages())
                : new JsonResult<List<Post> >(null, "0", "动态数量为0",pageInfo.getPages());
        return json;
    }

    @RequestMapping("/getFollowPosts")
    public JsonResult<List<Post> > getFollowPosts(Integer userId,Integer page) {
        PageHelper.startPage(page, 10);
        List<Post> res = postService.getFollowPosts(userId);
        PageInfo<Post> pageInfo = new PageInfo<Post>(res);
        JsonResult<List<Post> > json;
        json = !res.isEmpty()
                ? new JsonResult<List<Post> >(res, "1", "获取到动态",pageInfo.getPages())
                : new JsonResult<List<Post> >(null, "0", "动态数量为0",pageInfo.getPages());
        return json;
    }

    @RequestMapping("/likePost")
    public JsonResult<Integer> likePost(Integer userId ,Integer postId){
        JsonResult<Integer> json;
        try {
            Integer res = postService.likePost(userId ,postId);
            json = new JsonResult<>("1","点赞成功");
        }catch (Exception e){
            json = new JsonResult<>("0","已经点过赞了");
        }
        return json;
    }
    @RequestMapping("/cancelLikePost")
    public JsonResult<Integer> cancelLikePost(Integer userId ,Integer postId){
        JsonResult<Integer> json;
        Integer res = postService.cancelLikePost(userId ,postId);
        json = new JsonResult<>(Integer.toString(res),"");
        return json;
    }
    @RequestMapping("/addPost")
    public JsonResult<Integer> addPost(Post post){
        JsonResult<Integer> json;
        try {
            Integer res = postService.addPost(post);
            json = new JsonResult<>("1","发布动态成功");
        }catch (Exception e){
            logger.warn(e.toString());
            json = new JsonResult<>("0","发表失败");
        }
        return json;
    }
    @RequestMapping("/deletePost")
    public JsonResult<Integer> deletePost(Integer postId){
        JsonResult<Integer> json;
        try {
            Integer res = postService.deletePost(postId);
            json = new JsonResult<>("1","动态已删除");
        }catch (Exception e){
            logger.warn(e.toString());
            json = new JsonResult<>("0","删除动态失败");
        }
        return json;
    }
}
