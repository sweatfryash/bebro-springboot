package com.hch.springboot_mybatis.service;

import com.hch.springboot_mybatis.entity.Comment;
import com.hch.springboot_mybatis.entity.Post;
import com.hch.springboot_mybatis.entity.User;

import java.util.List;

public interface PostService {

    //根据用户id获取到其关注的用户们的动态
    List<Post> getFollowPosts(Integer userId);

    List<Post> getAllPostsByDate(Integer userId);

    List<Post> getPostsById(Integer askId,Integer userId);

    List<Post> getStarPosts(Integer userId);

    Integer likePost(Integer userId ,Integer postId);

    Integer cancelLikePost(Integer userId ,Integer postId);

    Integer starPost(Integer userId ,Integer postId);

    Integer cancelStarPost(Integer userId ,Integer postId);

    Integer addPost(Post post);

    Integer deletePost(Integer postId);

    Post getPostByPostId(Integer postId,Integer userId);

    Integer updatePostHot(Integer postId,double addHot);
    //转发动态的记录
    List<Post> getForwardPost(Integer postId);

    List<Post> getHotPost(Integer askId);

    List<Post> searchPost(Integer askId,String key,String orderBy);

    List<Post> searchFollowPost(Integer askId,String key);

    String getDateByPostId(Integer postId);
}
