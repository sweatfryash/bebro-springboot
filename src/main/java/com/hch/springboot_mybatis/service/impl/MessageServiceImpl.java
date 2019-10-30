package com.hch.springboot_mybatis.service.impl;

import com.hch.springboot_mybatis.entity.Message;
import com.hch.springboot_mybatis.mapper.MessageMapper;
import com.hch.springboot_mybatis.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("messageService")
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;
    @Override
    public int insertMessage(Message message) {
        return messageMapper.insertMessage(message);
    }

    @Override
    public List<Message> getAllMessages() {
        return messageMapper.getAllMessages();
    }

    @Override
    public List<Message> getFollowMessages(String email) {
        return messageMapper.getFollowMessages(email);
    }

    @Override
    public List<Message> getMessagesByEmail(String email) {
        return messageMapper.getMessagesByEmail(email);
    }
}
