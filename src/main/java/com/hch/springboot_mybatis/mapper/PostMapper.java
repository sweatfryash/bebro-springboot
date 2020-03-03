package com.hch.springboot_mybatis.mapper;

import com.hch.springboot_mybatis.entity.Comment;
import com.hch.springboot_mybatis.entity.Post;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PostMapper {
    //用户关注的用户的动态
    @Select("SELECT *,#{userId} as askId FROM post WHERE post.postId IN " +
            "(SELECT postId FROM post WHERE userId IN (SELECT followedId FROM fans WHERE fanId=#{userId})) ORDER BY postId DESC")
    @Results(id = "postMap",value = {
            @Result(id = true,property = "postId",column = "postId"),
            @Result(property = "isLiked",column = "{askId = askId,postId = postId}",
                    one = @One(select = "com.hch.springboot_mybatis.mapper.PostMapper.isLiked")),
            @Result(property = "likeNum",column = "postId",
                    one = @One(select = "com.hch.springboot_mybatis.mapper.PostMapper.getPostsLikeNum")),
            @Result(property="commentList",column = "{askId = askId,postId = postId}",
                    many = @Many(select = "com.hch.springboot_mybatis.mapper.CommentMapper.getCommentsByPostId"))})
    List<Post> getFollowPosts(Integer userId);
    //根据用户ID查其动态
    @Select("SELECT * ,#{askId} as askId from post where userId=#{userId} ORDER BY postId DESC")
    @ResultMap("postMap")
    List<Post> getPostsById(Integer askId,Integer userId);
    //按照时间排序的所有动态
    @Select("SELECT * ,#{userId} as askId from post ORDER BY postId DESC")
    @ResultMap("postMap")
    List<Post> getAllPostsByDate(Integer userId);
    //获取动态的点赞数
    @Select("select count(*) as likeNum from likePost where postId = #{postId}")
    List<Integer> getPostsLikeNum(Integer postId);

    //给动态点赞
    @Insert("insert into likePost (userId,postId) values(#{userId},#{postId})")
    Integer likePost(Integer userId ,Integer postId);
    //取消动态点赞
    @Delete("DELETE FROM likePost WHERE userId = #{userId} and postId = #{postId}")
    Integer cancelLikePost(Integer userId ,Integer postId);

    //是否给动态点赞了
    @Select("select count(*) from likePost where userId = #{askId} and postId = #{postId}")
    Integer isLiked(Integer askId,Integer postId);

    //添加动态
    @Insert("insert into post (userId,date,text,imageUrl) values(#{userId},#{date},#{text},#{imageUrl}) ")
    Integer addPost(Post post);
    //删除动态
    @Delete("delete from post where postId = #{postId}")
    Integer deletePost(Integer postId);
}
