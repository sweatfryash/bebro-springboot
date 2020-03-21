package com.hch.springboot_mybatis.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.Serializable;
import java.util.List;

@EntityScan
public class Post implements Serializable {

    private Integer postId;
    private Integer userId;
    private String username;
    private String date;
    private String text;
    private String imageUrl;
    private String avatarUrl;
    private Integer likeNum;
    private Integer commentNum;
    private Integer forwardNum;
    private Integer isLiked;
    private Integer isStar;
    //转发
    private Integer forwardId;
    private String forwardName;
    private String forwardText;
    private String forwardImageUrl;

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getIsLiked() {
        return isLiked;
    }

    public void setIsLiked(Integer isLiked) {
        this.isLiked = isLiked;
    }

    public Integer getIsStar() {
        return isStar;
    }

    public void setIsStar(Integer isStar) {
        this.isStar = isStar;
    }

    public Integer getForwardId() {
        return forwardId;
    }

    public void setForwardId(Integer forwardId) {
        this.forwardId = forwardId;
    }

    public String getForwardName() {
        return forwardName;
    }

    public void setForwardName(String forwardName) {
        this.forwardName = forwardName;
    }

    public String getForwardImageUrl() {
        return forwardImageUrl;
    }

    public void setForwardImageUrl(String forwardImageUrl) {
        this.forwardImageUrl = forwardImageUrl;
    }

    public Integer getForwardNum() {
        return forwardNum;
    }

    public void setForwardNum(Integer forwardNum) {
        this.forwardNum = forwardNum;
    }

    public String getForwardText() {
        return forwardText;
    }

    public void setForwardText(String forwardText) {
        this.forwardText = forwardText;
    }
}
