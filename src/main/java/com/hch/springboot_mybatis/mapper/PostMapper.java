package com.hch.springboot_mybatis.mapper;

import com.hch.springboot_mybatis.entity.Post;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PostMapper {

    //添加动态
    @Insert("insert into post(postId,userId,date,text,imageUrl) values(#{postId},#{userId},#{date},#{text},#{imageUrl})")
    int addPost(Post post);

    //获取所有动态
    @Select("select * from post order by date DESC")
    List<Post> getAllPosts();

    //获取指定用户关注的用户的动态
/*    @Select("SELECT * FROM message WHERE email IN" +
            "( SELECT useremail FROM message, fans WHERE email = fansemail AND fansemail = #{email} );")
    List<Message> getFollowMessages(String email);*/

    //根据邮箱获取动态
    @Select("select * from post where email=#{email}")
    List<Post> getPostsByEmail(String email);
}
