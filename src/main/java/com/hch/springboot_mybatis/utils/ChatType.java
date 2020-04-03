package com.hch.springboot_mybatis.utils;

public enum ChatType {

    REGISTER, SINGLE_SENDING, FILE_MSG_SINGLE_SENDING;
     
    public static void main(String[] args) {
        System.out.println(ChatType.REGISTER);
    }
}