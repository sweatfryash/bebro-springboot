package com.hch.springboot_mybatis.mapper;

import com.hch.springboot_mybatis.entity.Comment;
import com.hch.springboot_mybatis.entity.Reply;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyMapper {
    //根据commentId查询其所有的回复
    @Select("SELECT * ,#{askId} as askId FROM reply WHERE commentId = #{commentId} order by replyId DESC")
    @Results(id = "replyMap",value = {
            @Result(id = true,property = "replyId",column = "replyId"),
            @Result(property = "userId",column = "userId"),
            @Result(property = "avatarUrl",column = "userId",
                    one = @One(select = "com.hch.springboot_mybatis.mapper.UserMapper.getAvatarById")),
            @Result(property = "username",column = "userId",
                    one = @One(select = "com.hch.springboot_mybatis.mapper.UserMapper.getUsernameById")),
            @Result(property = "likeNum",column = "replyId",
                    one = @One(select = "com.hch.springboot_mybatis.mapper.ReplyMapper.getReplyLikeNum")),
            @Result(property = "isLiked",column = "{askId = askId,replyId = replyId}",
                    one = @One(select = "com.hch.springboot_mybatis.mapper.ReplyMapper.isLiked")),
    })
    List<Reply> getReplyByCommentId(Integer askId, Integer commentId);

    @Select("SELECT * ,#{askId} as askId FROM reply WHERE commentId = #{commentId} order by replyId DESC LIMIT 2")
    @ResultMap("replyMap")
    List<Reply> getTopReplyByCommentId(Integer askId, Integer commentId);

    @Select("Select count(*) from likeReply where userId=#{askId} and replyId = #{replyId}")
    Integer isLiked(Integer askId,Integer replyId);
    @Select("select count(*) from likeReply where replyId = #{replyId}")
    Integer getReplyLikeNum(Integer replyId);

    //给回复点赞
    @Insert("insert into likeReply (userId,replyId) values(#{userId},#{replyId})")
    Integer likeReply(Integer userId ,Integer replyId);
    //取消回复点赞
    @Delete("DELETE FROM likeReply WHERE userId = #{userId} and replyId = #{replyId}")
    Integer cancelLikeReply(Integer userId ,Integer replyId);
    //添加回复
    @Insert("insert into reply (userId,commentId,text,imageUrl,date,beReplyName) " +
            "values(#{userId},#{commentId},#{text},#{imageUrl},#{date},#{beReplyName}) ")
    Integer addReply(Reply reply);
    //删除回复
    @Delete("delete from reply where replyId = #{replyId}")
    Integer deleteReply(Integer replyId);
}
