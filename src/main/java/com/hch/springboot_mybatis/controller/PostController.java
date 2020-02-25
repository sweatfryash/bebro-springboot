package com.hch.springboot_mybatis.controller;

import com.hch.springboot_mybatis.entity.Post;
import com.hch.springboot_mybatis.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }

    @RequestMapping("/addPost")
    public int addPost(Post post){return postService.addPost(post);}

    @RequestMapping("/getAllPosts")
    public List<Post> getAllPosts(){return postService.getAllPosts();}

    @RequestMapping("/getPostsByEmail")
    public List<Post> getPostsByEmail(String email){return postService.getPostsByEmail(email);}

}
