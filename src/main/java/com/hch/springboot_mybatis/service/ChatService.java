package com.hch.springboot_mybatis.service;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;

public interface ChatService {

    public void register(JSONObject param, ChannelHandlerContext ctx);
    
    public void singleSend(JSONObject param, ChannelHandlerContext ctx);
    
    public void FileMsgSingleSend(JSONObject param, ChannelHandlerContext ctx);
    
    public void remove(ChannelHandlerContext ctx);
    
    public void typeError(ChannelHandlerContext ctx);
        
}
