package com.study.netty.firstchat.server.course16.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author 卫云鹏
 * @date in 11:26 2020/1/7
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponseHandler> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponseHandler msg) throws Exception {
        System.out.println("登录请求的响应。。。。");
    }
}
