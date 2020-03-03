package com.hch.springboot_mybatis.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import java.io.Serializable;

@EntityScan
public class User implements Serializable {

    private Integer userId;
    private String email;
    private String username;
    private String password;
    private String avatarUrl;
    private String bio;
    private String birthDay;
    private String gender;
    private String city;
    private String backImgUrl;
    private Integer postNum;    //动态数量
    private Integer fanNum;     //粉丝数
    private Integer followNum;  //关注数
    private Integer isFollow;   //是否是查询者的关注

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    //转换json返回时忽略密码这个属性
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Integer getPostNum() {
        return postNum;
    }

    public void setPostNum(Integer postNum) {
        this.postNum = postNum;
    }

    public Integer getFanNum() {
        return fanNum;
    }

    public void setFanNum(Integer fanNum) {
        this.fanNum = fanNum;
    }

    public Integer getFollowNum() {
        return followNum;
    }

    public void setFollowNum(Integer followNum) {
        this.followNum = followNum;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBackImgUrl() {
        return backImgUrl;
    }

    public void setBackImgUrl(String backImgUrl) {
        this.backImgUrl = backImgUrl;
    }

    public Integer getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(Integer isFollow) {
        this.isFollow = isFollow;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", bio='" + bio + '\'' +
                ", birthDay='" + birthDay + '\'' +
                ", gender='" + gender + '\'' +
                ", city='" + city + '\'' +
                ", backImgUrl='" + backImgUrl + '\'' +
                ", postNum=" + postNum +
                ", fanNum=" + fanNum +
                ", followNum=" + followNum +
                ", isFollow=" + isFollow +
                '}';
    }
}
