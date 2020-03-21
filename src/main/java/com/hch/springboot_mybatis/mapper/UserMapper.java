package com.hch.springboot_mybatis.mapper;

import com.hch.springboot_mybatis.entity.Updates;
import com.hch.springboot_mybatis.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;

//效果和@Component一样，不过是mapper类应该属于持久层所以用repository
@Repository
public interface UserMapper {

    //根据id查找用户信息##############
    @Select("select * ,#{askId} as askId from user WHERE userId  = #{userId} ")
    @Results(id = "userMap",value = {
            @Result(id = true,property = "userId",column = "userId"),
            @Result(property = "postNum",column = "userId",
                    one = @One(select = "com.hch.springboot_mybatis.mapper.UserMapper.getPostNumById")),
            @Result(property = "fanNum",column = "userId",
                    one = @One(select = "com.hch.springboot_mybatis.mapper.UserMapper.getFanNumById")),
            @Result(property = "followNum",column = "userId",
                    one = @One(select = "com.hch.springboot_mybatis.mapper.UserMapper.getFollowNumById")),
            @Result(property = "isFollow",column = "{askId = askId,userId = user.userId}",
                    one = @One(select = "com.hch.springboot_mybatis.mapper.UserMapper.isAFollow")),
    })
    User findUserById(@Param("askId") Integer askId, @Param("userId") Integer userId);
    @Select("select * ,#{askId} as askId from user WHERE username  = #{username} ")
    @ResultMap("userMap")
    User findUserByName(@Param("askId") Integer askId, String username);

    //添加用户或者说是注册操作
    @Insert({"insert into user(email,password) values(#{email},#{password})"})
    @Options(useGeneratedKeys=true,keyColumn="userId",keyProperty = "userId")
    Integer insertUser(User user);

    //登录操作，查询出结果就登录成功，查询结果空就失败
    @Select("SELECT USER.*,p.postNum,follow.followNum,fan.fanNum FROM " +
            "USER LEFT JOIN (SELECT userId ,count(*) AS postNum from post where userId = (select userId from user where email =  #{email})) as p ON USER.userId = p.userId " +
            "LEFT JOIN (SELECT fanId, count(*) AS followNum FROM fans WHERE fanId = (select userId from user where email = #{email} ) ) AS follow ON USER.userId = follow.fanId " +
            "LEFT JOIN (SELECT followedId, count(*) AS fanNum FROM fans WHERE followedId = (select userId from user where email = #{email} ) ) AS fan ON USER.userId = fan.followedId " +
            "WHERE email = #{email} and password = #{password}")
    User logIn(User user);

    //更新密码操作，更新成功返回1，否则返回0
    @Update("update user set password=#{password} where email=#{email}")
    int updatePwd(String email,String password);

    //更新用户信息操作，更新成功返回1，否则返回0
    @Update("update user set username=#{username},bio=#{bio},avatarUrl=#{avatarUrl} where email=#{email}")
    int updateUserDetail(User user);

    //查找用户关注的人
    @Select("select * ,#{userId}  as askId from user WHERE userId IN (SELECT followedId FROM fans WHERE fanId= #{userId})")
    @ResultMap("userMap")
    List<User> findFollow(Integer userId);

    //查找关注用户的人即粉丝
    @Select("select *,#{userId} as askId from user WHERE user.userId IN (SELECT fanId from fans WHERE followedId= #{userId})")
    @ResultMap("userMap")
    List<User> findFans(Integer userId);

    @Select("select *,#{askId} as askId from user WHERE user.userId IN (SELECT userId from likePost WHERE postId= #{postId})")
    @ResultMap("userMap")
    List<User> getLikedUser(Integer askId,Integer postId);
    @Select("SELECT *,#{askId} as askId from user where username like '%${key}%'")
    @ResultMap("userMap")
    List<User> searchUser(Integer askId,String key);
    //关注一个用户，提供关注者与被关注者
    @Insert("insert into fans (fanId,followedId) values(#{fanId},#{followedId})")
    int followAUser(Integer fanId, Integer followedId);
    //取关一个用户，提供关注者与被关注者
    @Delete("DELETE FROM fans WHERE fanId = #{fanId} and followedId = #{followedId}")
    int cancelFollowAUser(Integer fanId,Integer followedId);

    //通用修改个人信息
    @Update("update user set ${property} = #{value} where userId=#{userId}")
    Integer updateUserProperty(Integer userId, String property, String value);

    //查询用户名是否已存在
    @Select("select count(*) from user where username = #{username}")
    Integer isExistTheUsername(String username);

    //根据userId查询动态数量
    @Select("select count(*) from post where userId = #{userId}")
    Integer getPostNumById(Integer userId);
    //根据userId查询粉丝数量
    @Select("select count(*) from fans where followedId = #{userId}")
    Integer getFanNumById(Integer userId);
    //根据userId查询关注数量
    @Select("select count(*) from fans where fanId = #{userId}")
    Integer getFollowNumById(Integer userId);

    //查询者与被查询的关系
    @Select("SELECT count(*) from fans where fanId = #{askId} and followedId = #{userId}")
    Integer isAFollow(Integer askId, Integer userId);

    @Select("SELECT count(*) from fans where followedId = #{askId} and fanId = #{userId}")
    Integer isAFan(Integer askId, Integer userId);
    @Select("SELECT count(*) from user where email = #{email}")
    Integer isExistTheEmail(String email);

    @Select("select username from user where userId=#{userId}")
    String getUsernameById(Integer userId);
    @Select("select avatarUrl from user where userId=#{userId}")
    String getAvatarById(Integer userId);

    @Select("SELECT * FROM updates order by date DESC LIMIT 1 ")
    Updates checkUpdate();
}
