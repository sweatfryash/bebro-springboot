package com.hch.springboot_mybatis.mapper;

import com.hch.springboot_mybatis.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

//效果和@Component一样，不过是mapper类应该属于持久层所以用repository
@Repository
public interface UserMapper {

    @Select("select username,bio,avatarUrl from user where email=#{email}")
    User findByEmail(String email);


    //添加用户或者说是注册操作
    @Insert("insert into user(email,username,password,bio,avatarUrl) values(#{email},#{username},#{password},#{bio},#{avatarUrl})")
    int insertUser(User user);

    //登录操作，查询出结果就登录成功，查询结果空就失败
    @Select("select email,username,bio,avatarUrl from user where email=#{email} and password=#{password}")
    User logIn(User user);

    //更新密码操作，更新成功返回1，否则返回0
    @Update("update user set password=#{password} where email=#{email}")
    int updatePwd(String email,String password);

    //更新用户信息操作，更新成功返回1，否则返回0
    @Update("update user set username=#{username},bio=#{bio},avatarUrl=#{avatarUrl} where email=#{email}")
    int updateUserDetail(User user);

    //查找用户关注的人，参数的email对应 fans
    @Select("SELECT * FROM user WHERE email IN " +
            "( SELECT useremail FROM user, fans WHERE email = fansemail AND fansemail = #{email} );")
    List<User> findFollow(String email);

    //查找关注用户的人即粉丝
    @Select("SELECT * FROM user WHERE email IN " +
            "( SELECT fansemail FROM user, fans WHERE email = useremail AND useremail = #{email} );")
    List<User> findFans(String email);
}
