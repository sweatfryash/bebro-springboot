package com.hch.springboot_mybatis.service.impl;

import com.hch.springboot_mybatis.entity.User;
import com.hch.springboot_mybatis.mapper.UserMapper;
import com.hch.springboot_mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByEmail(String email) {
        return userMapper.findByEmail(email);
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
    public List<User> findFollow(String email) {
        return userMapper.findFollow(email);
    }

    @Override
    public List<User> findFans(String email) {
        return userMapper.findFans(email);
    }


}
