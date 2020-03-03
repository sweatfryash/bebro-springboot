package com.hch.springboot_mybatis.mapper;

import com.hch.springboot_mybatis.entity.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper {
    //根据postId查询其所有的评论
    @Select("SELECT * ,#{askId} as askId FROM comments WHERE postId = #{postId}")
    @Results(id = "commentMap",value = {
            @Result(id = true,property = "commentId",column = "commentId"),
            @Result(property = "likeNum",column = "commentId",
                    one = @One(select = "com.hch.springboot_mybatis.mapper.CommentMapper.getCommentLikeNum")),
            @Result(property = "isLiked",column = "{askId = askId,commentId = commentId}",
                    one = @One(select = "com.hch.springboot_mybatis.mapper.CommentMapper.isLiked"))
    })
    List<Comment> getCommentsByPostId(Integer askId,Integer postId);

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
}
