package com.hch.springboot_mybatis.utils;

import org.springframework.stereotype.Component;

@Component
public class RandomCodeUtil {
    //获取随机的验证码
    public  String getRandomCode() {
        String[] letters = new String[] {"0","1","2","3","4","5","6","7","8","9"};
        String code ="";
        for (int i = 0; i < 4; i++) {
            code = code + letters[(int)Math.floor(Math.random()*letters.length)];
        }
        return code;
    }
}
