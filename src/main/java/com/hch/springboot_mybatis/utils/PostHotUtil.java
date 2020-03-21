package com.hch.springboot_mybatis.utils;

import com.hch.springboot_mybatis.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class PostHotUtil {

    private final PostService postService;
    @Autowired
    public PostHotUtil(PostService postService){
        this.postService = postService;
    }
    public void countHot(Integer postId,double baseHot){
        String date = postService.getDateByPostId(postId);
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );
        Date now = new Date();
        double hot =0.0;
        try {
           Date addDate = sdf.parse(date);
           int h = (int)((now.getTime()-addDate.getTime())/(1000 * 60 * 60));
            hot= (baseHot / Math.exp(0.064 * h));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DecimalFormat df = new DecimalFormat("#.###");
        hot=Double.parseDouble(df.format(hot));
        postService.updatePostHot(postId,hot);
    }

}
