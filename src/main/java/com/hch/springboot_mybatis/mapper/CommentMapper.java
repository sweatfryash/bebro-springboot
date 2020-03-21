package com.hch.springboot_mybatis.mapper;

import com.hch.springboot_mybatis.entity.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper {
    //根据postId查询其所有的评论
    @Select("SELECT * ,#{askId} as askId FROM comments WHERE postId = #{postId} order by commentId DESC")
    @Results(id = "commentMap",value = {
            @Result(id = true,property = "commentId",column = "commentId"),
            @Result(property = "userId",column = "userId"),
            @Result(property = "avatarUrl",column = "userId",
                    one = @One(select = "com.hch.springboot_mybatis.mapper.UserMapper.getAvatarById")),
            @Result(property = "username",column = "userId",
                    one = @One(select = "com.hch.springboot_mybatis.mapper.UserMapper.getUsernameById")),
            @Result(property = "likeNum",column = "commentId",
                    one = @One(select = "com.hch.springboot_mybatis.mapper.CommentMapper.getCommentLikeNum")),
            @Result(property = "isLiked",column = "{askId = askId,commentId = commentId}",
                    one = @One(select = "com.hch.springboot_mybatis.mapper.CommentMapper.isLiked")),
            @Result(property = "replyNum",column = "commentId",
                    one = @One(select = "com.hch.springboot_mybatis.mapper.CommentMapper.getReplyNumByCommentId")),
            @Result(property="replyList",column = "{askId = askId,commentId = commentId}",
                    many = @Many(select = "com.hch.springboot_mybatis.mapper.ReplyMapper.getTopReplyByCommentId"))
    })
    List<Comment> getCommentByPostId(Integer askId,Integer postId);

    //根据commentId查评论的点赞数
    @Select("select count(*) from likeComment where commentId = #{commentId}")
    Integer getCommentLikeNum(Integer commentId);
    //给评论点赞
    @Insert("insert into likeComment (userId,commentId) values(#{userId},#{commentId})")
    Integer likeComment(Integer userId ,Integer commentId);
    //取消评论点赞
    @Delete("DELETE FROM likeComment WHERE userId = #{userId} and commentId = #{commentId}")
    Integer cancelLikeComment(Integer userId ,Integer commentId);
    //是否给评论点赞了
    @Select("select count(*) from likeComment where userId = #{askId} and commentId = #{commentId}")
    Integer isLiked(Integer askId,Integer commentId);
    //添加评论
    @Insert("insert into comments (userId,postId,imageUrl,text,date) " +
            "values(#{userId},#{postId},#{imageUrl},#{text},#{date}) ")
    Integer addComment(Comment comment);
    //删除评论
    @Delete("delete from comments where commentId = #{commentId}")
    Integer deleteComment(Integer commentId);
    @Select("select count(*) as replyNum from reply where commentId = #{commentId}")
    Integer getReplyNumByCommentId(Integer commentId);
}
