package com.hch.springboot_mybatis.mapper;

import com.hch.springboot_mybatis.entity.Post;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PostMapper {
    //用户关注的用户的动态
    @Select("SELECT *,#{userId} as askId FROM post WHERE post.postId IN " +
            "(SELECT postId FROM post WHERE userId IN (SELECT followedId FROM fans WHERE fanId=#{userId} union select #{userId})) ORDER BY postId DESC")
    @Results(id = "postMap",value = {
            @Result(id = true,property = "postId",column = "postId"),
            @Result(property = "userId",column = "userId"),
            @Result(property = "forwardId",column = "forwardId"),
            @Result(property = "isLiked",column = "{askId = askId,postId = postId}",
                    one = @One(select = "com.hch.springboot_mybatis.mapper.PostMapper.isLiked")),
            @Result(property = "likeNum",column = "postId",
                    one = @One(select = "com.hch.springboot_mybatis.mapper.PostMapper.getPostsLikeNum")),
            @Result(property = "forwardNum",column = "postId",
                    one = @One(select = "com.hch.springboot_mybatis.mapper.PostMapper.getPostsForwardNum")),
            @Result(property = "commentNum",column = "postId",
                    one = @One(select = "com.hch.springboot_mybatis.mapper.PostMapper.getPostsCommentNum")),
            @Result(property = "avatarUrl",column = "userId",
                    one = @One(select = "com.hch.springboot_mybatis.mapper.UserMapper.getAvatarById")),
            @Result(property = "username",column = "userId",
                    one = @One(select = "com.hch.springboot_mybatis.mapper.UserMapper.getUsernameById")),
            @Result(property = "isStar",column = "{askId = askId,postId = postId}",
                    one = @One(select = "com.hch.springboot_mybatis.mapper.PostMapper.isStar")),
            @Result(property = "forwardName",column = "forwardId",
                    one = @One(select = "com.hch.springboot_mybatis.mapper.PostMapper.getNameByPostId")),
            @Result(property = "forwardText",column = "forwardId",
                    one = @One(select = "com.hch.springboot_mybatis.mapper.PostMapper.getTextByPostId")),
            @Result(property = "forwardImageUrl",column = "forwardId",
                    one = @One(select = "com.hch.springboot_mybatis.mapper.PostMapper.getImageByPostId")),
            })
    List<Post> getFollowPosts(Integer userId);
    //根据用户ID查其动态
    @Select("SELECT * ,#{askId} as askId from post where userId=#{userId} ORDER BY postId DESC")
    @ResultMap("postMap")
    List<Post> getPostsByUserId(Integer askId, Integer userId);
    //按照时间排序的所有动态
    @Select("SELECT * ,#{userId} as askId from post ORDER BY postId DESC")
    @ResultMap("postMap")
    List<Post> getAllPostsByDate(Integer userId);

    @Select("SELECT * ,1 as askId FROM post WHERE forwardId = #{postId}  ORDER BY postId DESC")
    @ResultMap("postMap")
    List<Post> getForwardPost(Integer postId);

    @Select("SELECT *,#{userId} as askId FROM post WHERE post.postId " +
            "IN (select postId from star where userId=#{userId}) ORDER BY postId DESC")
    @ResultMap("postMap")
    List<Post> getStarPosts(Integer userId);

    @Select("SELECT *,#{askId} as askId FROM post ORDER BY hot DESC")
    @ResultMap("postMap")
    List<Post> getHotPost(Integer askId);

    @Select("SELECT *,#{askId} as askId FROM post where text like '%${key}%' ORDER BY ${orderBy} DESC")
    @ResultMap("postMap")
    List<Post> searchPost(Integer askId, String key, String orderBy);
    @Select("SELECT *,#{askId} as askId FROM post where text like '%${key}%' and post.postId IN " +
            " (SELECT postId FROM post WHERE userId IN (SELECT followedId FROM fans WHERE fanId=#{askId})) ORDER BY postId DESC")
    @ResultMap("postMap")
    List<Post> searchFollowPost(Integer askId, String key);
    //获取动态的点赞数
    @Select("select count(*) as likeNum from likePost where postId = #{postId}")
    List<Integer> getPostsLikeNum(Integer postId);
    //获取动态的转发数
    @Select("select count(*) as forwardNum from post where forwardId = #{postId}")
    List<Integer> getPostsForwardNum(Integer postId);
    //获取动态的评论数
    @Select("SELECT sum(num) from (SELECT count(*) as num from comments where comments.postId = #{postId} UNION ALL" +
            " SELECT count(*) as num from reply where reply.commentId in " +
            " (SELECT commentId from comments where postId = #{postId})) AS n")
    List<Integer> getPostsCommentNum(Integer postId);

    //给动态点赞
    @Insert("insert into likePost (userId,postId) values(#{userId},#{postId})")
    Integer likePost(Integer userId ,Integer postId);
    //取消动态点赞
    @Delete("DELETE FROM likePost WHERE userId = #{userId} and postId = #{postId}")
    Integer cancelLikePost(Integer userId ,Integer postId);
    //给动态点赞
    @Insert("insert into star (userId,postId) values(#{userId},#{postId})")
    Integer starPost(Integer userId ,Integer postId);
    //取消动态点赞
    @Delete("DELETE FROM star WHERE userId = #{userId} and postId = #{postId}")
    Integer cancelStarPost(Integer userId ,Integer postId);

    //是否给动态点赞了
    @Select("select count(*) from likePost where userId = #{askId} and postId = #{postId}")
    Integer isLiked(Integer askId,Integer postId);
    //是否收藏了这条动态
    @Select("select count(*) from star where userId = #{askId} and postId = #{postId}")
    Integer isStar(Integer askId,Integer postId);

    //添加动态
    @Insert("insert into post (userId,date,text,imageUrl,forwardId) values(#{userId},#{date},#{text},#{imageUrl},#{forwardId}) ")
    Integer addPost(Post post);
    //删除动态
    @Delete("delete from post where postId = #{postId}")
    Integer deletePost(Integer postId);

    @Select("select username from user where userId = (select userId from post where postId = #{forwardId} )")
    String getNameByPostId(Integer forwardId);
    @Select("select text from post where postId = #{forwardId}")
    String getTextByPostId(Integer forwardId);
    @Select("select imageUrl from post where postId = #{forwardId}")
    String getImageByPostId(Integer forwardId);

    @Select("select *,#{userId} as askId from post where postId = #{postId}")
    @ResultMap("postMap")
    Post getPostByPostId(Integer postId,Integer userId);

    //增加热度
    @Update("update post set hot=hot+${addHot} where postId =#{postId}")
    Integer updateHot(Integer postId, double addHot);
    @Select("select date from post where postId = #{postId}")
    String getDateByPostId(Integer postId);
}
