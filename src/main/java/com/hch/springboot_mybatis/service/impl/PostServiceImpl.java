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
    public int addPost(Post post) {
        return postMapper.addPost(post);
    }

    @Override
    public List<Post> getAllPosts() {
        return postMapper.getAllPosts();
    }

    @Override
    public List<Post> getPostsByEmail(String email) {
        return postMapper.getPostsByEmail(email);
    }
}
