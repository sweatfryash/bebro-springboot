package com.hch.springboot_mybatis.controller;

import com.hch.springboot_mybatis.entity.Message;
import com.hch.springboot_mybatis.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;
    @Autowired
    public MessageController(MessageService messageService){
        this.messageService=messageService;
    }
    @RequestMapping("/insertMessage")
    public int insertMessage(Message message){return messageService.insertMessage(message);}

    @RequestMapping("/getAllMessages")
    public List<Message> getAllMessages(){return messageService.getAllMessages();}

    @RequestMapping("/getFollowMessages")
    public List<Message> getFollowMessages(String email){return messageService.getFollowMessages(email);}

    @RequestMapping("/getMessagesByEmail")
    public List<Message> getMessagesByEmail(String email){return messageService.getMessagesByEmail(email);}

}
