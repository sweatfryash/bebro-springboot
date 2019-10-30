package com.hch.springboot_mybatis.mapper;

import com.hch.springboot_mybatis.entity.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageMapper {

    //添加动态
    @Insert("insert into message(messageId,email,date,text,imageUrl) values(#{messageId},#{email},#{date},#{text},#{imageUrl})")
    int insertMessage(Message message);

    //获取所有动态
    @Select("select * from message order by date DESC")
    List<Message> getAllMessages();

    //获取指定用户关注的用户的动态
    @Select("SELECT * FROM message WHERE email IN" +
            "( SELECT useremail FROM message, fans WHERE email = fansemail AND fansemail = #{email} );")
    List<Message> getFollowMessages(String email);

    //根据邮箱获取动态
    @Select("select * from message where email=#{email}")
    List<Message> getMessagesByEmail(String email);
}
