package com.hch.springboot_mybatis.service.impl;

import com.hch.springboot_mybatis.entity.Post;
import com.hch.springboot_mybatis.mapper.PostMapper;
import com.hch.springboot_mybatis.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("postService")
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper ;
    @Autowired
    public PostServiceImpl(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    @Override
    public List<Post> getFollowPosts(Integer userId) {
        return postMapper.getFollowPosts(userId);
    }

    @Override
    public List<Post> getAllPostsByDate(Integer userId) {
        return postMapper.getAllPostsByDate(userId);
    }

    @Override
    public List<Post> getPostsById(Integer askId, Integer userId) {
        return postMapper.getPostsByUserId(askId, userId);
    }

    @Override
    public List<Post> getStarPosts(Integer userId) {
        return postMapper.getStarPosts(userId);
    }

    @Override
    public Integer likePost(Integer userId, Integer postId) {
        return postMapper.likePost(userId, postId);
    }

    @Override
    public Integer cancelLikePost(Integer userId, Integer postId) {
        return postMapper.cancelLikePost(userId,postId);
    }

    @Override
    public Integer starPost(Integer userId, Integer postId) {
        return postMapper.starPost(userId, postId);
    }

    @Override
    public Integer cancelStarPost(Integer userId, Integer postId) {
        return postMapper.cancelStarPost(userId, postId);
    }

    @Override
    public Integer addPost(Post post) {
        return postMapper.addPost(post);
    }

    @Override
    public Integer deletePost(Integer postId) {
        return postMapper.deletePost(postId);
    }

    @Override
    public Post getPostByPostId(Integer postId, Integer userId) {
        return postMapper.getPostByPostId(postId, userId);
    }

    @Override
    public Integer updatePostHot(Integer postId, double addHot) {
        return postMapper.updateHot(postId, addHot);
    }

    @Override
    public List<Post> getForwardPost(Integer postId) {
        return postMapper.getForwardPost(postId);
    }

    @Override
    public List<Post> getHotPost(Integer askId) {
        return postMapper.getHotPost(askId);
    }

    @Override
    public List<Post> searchPost(Integer askId, String key, String orderBy) {
        return postMapper.searchPost(askId, key, orderBy);
    }

    @Override
    public List<Post> searchFollowPost(Integer askId, String key) {
        return postMapper.searchFollowPost(askId, key);
    }

    @Override
    public String getDateByPostId(Integer postId) {
        return postMapper.getDateByPostId(postId);
    }
}
