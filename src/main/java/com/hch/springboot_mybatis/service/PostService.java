package com.hch.springboot_mybatis.service;

import com.hch.springboot_mybatis.entity.Post;
import java.util.List;

public interface PostService {

    //插入一条新的动态
    int addPost(Post post);
    //获取数据库中所有的动态数据，不需要参数
    List<Post> getAllPosts();
    //获取指定用户的所有动态
    List<Post> getPostsByEmail(String email);
}
