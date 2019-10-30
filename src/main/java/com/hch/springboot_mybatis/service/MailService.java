package com.hch.springboot_mybatis.service;

public interface MailService {
    /**
     * 发送HTML邮件
     * @param to 收件人
     * @param code 验证码（已被包围在<html>标签内）
     */
    void sendHtmlMail(String to, String code);
}
