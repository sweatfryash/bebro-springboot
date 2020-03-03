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
        return postMapper.getPostsById(askId, userId);
    }

    @Override
    public Integer likePost(Integer userId, Integer postId) {
        return postMapper.likePost(userId, postId);
    }

    @Override
    public Integer cancelLikePost(Integer userId, Integer postId) {
        return postMapper.cancelLikePost(userId,postId);
    }
}
