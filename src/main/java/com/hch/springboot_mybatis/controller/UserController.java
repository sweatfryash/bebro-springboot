package com.hch.springboot_mybatis.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hch.springboot_mybatis.entity.Updates;
import com.hch.springboot_mybatis.entity.User;
import com.hch.springboot_mybatis.service.MailService;
import com.hch.springboot_mybatis.service.UserService;
import com.hch.springboot_mybatis.utils.Constant;
import com.hch.springboot_mybatis.utils.JsonResult;
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

    private final UserService userService;
    private final MailService mailService;
    private final RandomCodeUtil randomCodeUtil;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public UserController(UserService userService, MailService mailService, RandomCodeUtil randomCodeUtil) {
        this.userService = userService;
        this.mailService = mailService;
        this.randomCodeUtil = randomCodeUtil;
    }
    //根据ID查找用户
    @RequestMapping("/findUserById")
    public JsonResult<User> findUserById(Integer askId,Integer userId) {
        User res =userService.findUserById(askId,userId);
        JsonResult<User> json;
        json = res != null
                ? new JsonResult<>(res, "1", "查询成功")
                : new JsonResult<>(null, "0", "查询不到该userId");
        return json;
    }
    @RequestMapping("/findUserByName")
    public JsonResult<User> findUserByName(Integer askId,String username) {
        User res =userService.findUserByName(askId,username);
        JsonResult<User> json;
        json = res != null
                ? new JsonResult<>(res, "1", "查询成功")
                : new JsonResult<>(null, "0", "查询不到该userId");
        return json;
    }
    //登录
    @RequestMapping("/logIn")
    public JsonResult<User> logIn(User user,HttpSession session) {
        User res = userService.logIn(user);
        if(res!=null){
            session.setAttribute(Constant.USER_TOKEN, res.getUserId());
        }
        JsonResult<User> json;
        json = res != null
                ? new JsonResult<>(res, "1", "验证成功")
                : new JsonResult<>(null, "0", "验证失败");
        return json;
    }

    //添加成功返回1，捕获到异常既用户已存在返回0,验证码不符返回-1
    @RequestMapping("/addUser")
    public JsonResult<User>  addUser(String email, String password, String code, HttpSession session) {
        JsonResult<User> json;
        //获得session里存的验证码用来比对
        String sessionCode;
        //若session中没有code返回-2
        try {
            sessionCode = session.getAttribute("code").toString();
        } catch (Exception e) {
            logger.error("session中没有code" + e.toString());
            json =  new JsonResult<>(null, "-2", "验证码不符或已经超时了");
            return json;
        }
        User user = new User(email, password);
        if (code.equals(sessionCode)) {
            if(userService.isExistTheEmail(email) == 0){
                session.setAttribute("code", "null");
                userService.insertUser(user);
                logger.info(user.toString());
                json =  new JsonResult<>(user, "1", "注册成功请前往登录");
            } else{
                json =  new JsonResult<>(null, "0", "邮箱已经注册过了");
            }
        } else {
            json =  new JsonResult<>(null, "-1", "验证码不符");
        }
        return json;
    }

    //更新密码
    @RequestMapping("/updatePwd")
    public int updatePwd(String email, String password, String code, HttpSession session) {
        //获得session里存的验证码用来比对
        String sessionCode;
        //若session中没有code返回-2
        try {
            sessionCode = session.getAttribute("code").toString();
        } catch (Exception e) {
            logger.error("session中没有code" + e.toString());
            return -2;
        }
        if (code.equals(sessionCode)) {
            try {
                session.setAttribute("code", "null");
                return userService.updatePwd(email, password);
            } catch (Exception e) {
                logger.warn(e.toString());
                return 0;
            }
        } else {
            return -1;
        }
    }

    //更新用户信息
    @RequestMapping("/updateUserDetail")
    public int updateUserDetail(User user) {
        return userService.updateUserDetail(user);
    }
    //处理发送验证码的请求，生成一个随机验证码，添加到邮件中发送出去还要把code保存下来
    @RequestMapping("/sendEmail")
    public int sendEmail(String email, HttpSession session) {
        String code = randomCodeUtil.getRandomCode();
        session.setAttribute("code", code);
        logger.info(code);
        try {
            mailService.sendHtmlMail(email, code);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //查找用户关注的人
    @RequestMapping("/findFollow")
    public JsonResult<List<User>> findFollow(Integer userId,Integer page) {
        PageHelper.startPage(page, 20);
        List<User> res = userService.findFollow(userId);
        PageInfo<User> pageInfo = new PageInfo<>(res);
        JsonResult<List<User>> json;
        json = !res.isEmpty()
                ? new JsonResult<>(res, "1", "查询成功",pageInfo.getPages())
                : new JsonResult<>(res, "0", "没有正在关注的人",pageInfo.getPages());
        return json;
    }
    @RequestMapping("/searchUser")
    public JsonResult<List<User>> searchUser(Integer askId,String key,Integer page) {
        PageHelper.startPage(page, 20);
        List<User> res = userService.searchUser(askId, key);
        PageInfo<User> pageInfo = new PageInfo<>(res);
        JsonResult<List<User>> json;
        json = new JsonResult<>(res, "1", "查询成功",pageInfo.getPages());
        return json;
    }

    //查找用户的粉丝
    @RequestMapping("/findFan")
    public JsonResult<List<User>> findFan(Integer userId,Integer page) {
        PageHelper.startPage(page, 20);
        List<User> res = userService.findFan(userId);
        PageInfo<User> pageInfo = new PageInfo<>(res);
        JsonResult<List<User>> json;
        json = !res.isEmpty()
                ? new JsonResult<>(res, "1", "查询成功", pageInfo.getPages())
                : new JsonResult<>(res, "0", "没得粉丝", pageInfo.getPages());
        return json;
    }
    @RequestMapping("/getLikedUser")
    public JsonResult<List<User>> getLikedUser(Integer askId,Integer postId,Integer page) {
        PageHelper.startPage(page, 20);
        List<User> res = userService.getLikedUser(askId,postId);
        PageInfo<User> pageInfo = new PageInfo<>(res);
        JsonResult<List<User>> json;
        json = !res.isEmpty()
                ? new JsonResult<>(res, "1", "查询成功", pageInfo.getPages())
                : new JsonResult<>(res, "0", "没得点赞", pageInfo.getPages());
        return json;
    }

    @RequestMapping("/followAUser")
    public  JsonResult<Integer> followAUser(Integer fanId,Integer followedId){
        JsonResult<Integer> json;
        try{
            Integer res = userService.followAUser(fanId,followedId);
            json = new JsonResult<>("1","关注成功");
        }catch (Exception e){
            json = new JsonResult<>("0","已经关注过了");
            //logger.info("重复关注"+e.toString());
        }
        return json;
    }
    @RequestMapping("/cancelFollowAUser")
    public  JsonResult<Integer> cancelFollowAUser(Integer fanId,Integer followedId){
        JsonResult<Integer> json;
        int res = userService.cancelFollowAUser(fanId,followedId);
        json = new JsonResult<>(Integer.toString(res),"取消关注成功");
        return json;
    }
    @RequestMapping("/updateUserProperty")
    public  JsonResult<Integer> updateUserProperty(Integer userId,String property,String value){
        //logger.warn(value);
        JsonResult<Integer> json;
        Integer res = userService.updateUserProperty(userId,property,value);
        json = new JsonResult<>(Integer.toString(res),"");
        return json;
    }

    @RequestMapping("/isExistTheUsername")
    public  JsonResult<Integer> isExistTheUsername(String username){
        JsonResult<Integer> json;
        Integer res = userService.isExistTheUsername(username);
        json = new JsonResult<>(Integer.toString(res),"");
        return json;
    }
    @RequestMapping("/checkUpdate")
    public  JsonResult<Updates> checkUpdate(){
        JsonResult<Updates> json;
        try{
            Updates res = (Updates) userService.checkUpdate();
            json = new JsonResult<>(res,"1","检测成功");
        }catch (Exception e){
            logger.error(e.toString());
            json = new JsonResult<>("0","未知错误");
        }
        return json;
    }

}
