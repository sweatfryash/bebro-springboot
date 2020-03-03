package com.hch.springboot_mybatis.service.impl;

import com.hch.springboot_mybatis.entity.User;
import com.hch.springboot_mybatis.mapper.UserMapper;
import com.hch.springboot_mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User findUserById(Integer askId,Integer userId) {
        return userMapper.findUserById(askId,userId);
    }

    @Override
    public int insertUser(User user) {
        return userMapper.insertUser( user);
    }

    @Override
    public User logIn(User user) {
        return userMapper.logIn(user);
    }


    @Override
    public int updatePwd(String email,String password) {
        return userMapper.updatePwd(email,password);
    }

    @Override
    public int updateUserDetail(User user) {
        return userMapper.updateUserDetail(user);
    }

    @Override
    public List<User> findFollow(Integer userId) {
        return userMapper.findFollow(userId);
    }

    @Override
    public List<User> findFan(Integer userId) {
        return userMapper.findFans(userId);
    }

    @Override
    public int followAUser(Integer fanId, Integer followedId) {
        return userMapper.followAUser(fanId,followedId);
    }

    @Override
    public int cancelFollowAUser(Integer fanId, Integer followedId) {
        return userMapper.cancelFollowAUser(fanId,followedId);
    }

    @Override
    public Integer updateUserProperty(Integer userId, String property, String value) {
        return userMapper.updateUserProperty(userId,property,value);
    }

    @Override
    public Integer isExistTheUsername(String username) {
        return userMapper.isExistTheUsername(username);
    }


}
