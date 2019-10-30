package com.hch.springboot_mybatis.controller;

import com.hch.springboot_mybatis.entity.User;
import com.hch.springboot_mybatis.service.MailService;
import com.hch.springboot_mybatis.service.UserService;
import com.hch.springboot_mybatis.utils.RandomCodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final UserService userService;
    private final MailService mailService;
    private final RandomCodeUtil randomCodeUtil;

    @Autowired
    public UserController(UserService userService, MailService mailService,RandomCodeUtil randomCodeUtil){
        this.userService=userService;
        this.mailService=mailService;
        this.randomCodeUtil=randomCodeUtil;
    }

    /*-----下面为app里会用且必须要有的方法------*/

    @RequestMapping("/findByEmail")
    public User findByEmail(String email){
        return userService.findByEmail(email);
    }
    //登录成功返回1 否则 0
    @RequestMapping("/logIn")
    public User logIn(User user) {
        return userService.logIn(user);
    }

    //添加成功返回1，捕获到异常既用户已存在返回0,验证码不符返回-1
    @RequestMapping("/insertUser")
    public int insertUser(String email,String username,String password,String avatarUrl,String bio,String code, HttpSession session) {
        //获得session里存的验证码用来比对
        String sessionCode;
        //若session中没有code返回-2
        try {
            sessionCode=session.getAttribute("code").toString();
        }catch (Exception e){
            logger.error("session中没有code"+e.toString());
            return -2;
        }
        User user=new User(email,username,password,avatarUrl,bio);
        if (code.equals(sessionCode)){
            try {
                session.setAttribute("code","null");
                return userService.insertUser(user);
            } catch (Exception e) {
                logger.warn("-----添加用户时邮箱重复sql异常，异常已被catch捕获------\n" + e.toString());
                return 0;
            }
        }else {
            return -1;
        }
    }
    //更新密码
    @RequestMapping("/updatePwd")
    public int updatePwd(String email,String password,String code,HttpSession session) {
        //获得session里存的验证码用来比对
        String sessionCode;
        //若session中没有code返回-2
        try {
            sessionCode=session.getAttribute("code").toString();
        }catch (Exception e){
            logger.error("session中没有code"+e.toString());
            return -2;
        }
        if (code.equals(sessionCode)){
            try {
                session.setAttribute("code","null");
                return userService.updatePwd(email,password);
            } catch (Exception e) {
                logger.warn( e.toString());
                return 0;
            }
        }else {
            return -1;
        }
    }

    //更新用户信息
    @RequestMapping("/updateUserDetail")
    public int updateUserDetail(User user){return userService.updateUserDetail(user);}

    //处理发送验证码的请求，生成一个随机验证码，添加到邮件中发送出去还要把code保存下来
    @RequestMapping("/sendEmail")
    public int sendEmail(String email, HttpSession session){
        String code=randomCodeUtil.getRandomCode();
        session.setAttribute("code",code);
        logger.warn(code);
        try{
            mailService.sendHtmlMail(email,code);
            return 1;
        }catch (Exception e){
            logger.error( e.toString());
            return 0;
        }
    }

    //查找用户关注的人，参数的email对应 fans
    @RequestMapping("/findFollow")
    public List<User> findFollow(String email){
        return userService.findFollow(email);
    }

    //查找用户关注的人，参数的email对应 fans
    @RequestMapping("/findFans")
    public List<User> findFans(String email){
        return userService.findFans(email);
    }


}
