package com.hch.springboot_mybatis.service;

import com.hch.springboot_mybatis.entity.User;

import java.util.List;

public interface UserService {

    //根据email查找用户
    User findByEmail(String email);
    //用户注册，添加成功返回1，否则会抛出异常（使用trycatch捕获异常返回 0 -2019年10月19日10:24:37）
    int insertUser(User user);

    //用户登录，
    User logIn(User user);

    //更新密码，更新成功返回1，否则返回0
    int updatePwd(String email,String password);

    //用户更改信息
    int updateUserDetail(User user);

    //查找用户关注的人，参数的email对应 fans
    List<User> findFollow(String email);

    //查找用户的粉丝
    List<User> findFans(String email);

    //查询是否已经存在某个用户配合添加用户使用（暂时不需要了）
    //User isExisted();

}
