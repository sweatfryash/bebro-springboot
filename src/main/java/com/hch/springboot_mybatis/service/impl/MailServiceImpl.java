package com.hch.springboot_mybatis.service.impl;

import com.hch.springboot_mybatis.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service("mailService")
public class MailServiceImpl implements MailService {

    private final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendHtmlMail(String to, String code) {
        logger.info("发送HTML邮件开始："+to);
        //使用MimeMessage，MIME协议
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper;
        //MimeMessageHelper帮助我们设置更丰富的内容
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject("BeBro验证码");
            //true代表支持html
            helper.setText("这是来自BeBro的验证码邮件：<h3 style='color:skyblue;'>" + code + "</h3><br/>", true);
            mailSender.send(message);
            logger.info("发送HTML邮件成功"+to);
        } catch (MessagingException e) {
            logger.error("发送HTML邮件失败：", e);
        }
    }
}
