package com.hch.springboot_mybatis.service;

import com.hch.springboot_mybatis.entity.Comment;
import com.hch.springboot_mybatis.entity.Post;
import java.util.List;

public interface PostService {

    //根据用户id获取到其关注的用户们的动态
    List<Post> getFollowPosts(Integer userId);

    List<Post> getAllPostsByDate(Integer userId);

    List<Post> getPostsById(Integer askId,Integer userId);

    Integer likePost(Integer userId ,Integer postId);

    Integer cancelLikePost(Integer userId ,Integer postId);

    Integer addPost(Post post);

    Integer deletePost(Integer postId);
}
