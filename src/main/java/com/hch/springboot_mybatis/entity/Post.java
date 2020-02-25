package com.hch.springboot_mybatis.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.Serializable;

@EntityScan
public class Post implements Serializable {

    private String postId;
    private Integer userId;
    private String date;
    private String text;
    private String imageUrl;
    private String avatarUrl;

    public Post() {
    }

    public Post(String postId, Integer userId, String date, String text, String imageUrl, String avatarUrl) {
        this.postId = postId;
        this.userId = userId;
        this.date = date;
        this.text = text;
        this.imageUrl = imageUrl;
        this.avatarUrl = avatarUrl;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId='" + postId + '\'' +
                ", userId=" + userId +
                ", date='" + date + '\'' +
                ", text='" + text + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }
}
