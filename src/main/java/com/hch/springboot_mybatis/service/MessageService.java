package com.hch.springboot_mybatis.service;

import com.hch.springboot_mybatis.entity.Message;

import java.util.List;

public interface MessageService {

    //插入一条新的动态
    int insertMessage(Message message);
    //获取数据库中所有的动态数据，不需要参数
    List<Message> getAllMessages();
    //获取指定用户关注的用户的动态
    List<Message> getFollowMessages(String email);
    //获取指定用户的所有动态
    List<Message> getMessagesByEmail(String email);
}
