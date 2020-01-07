package com.study.netty.firstchat.server.course16.server.handler;

import com.study.netty.firstchat.server.course16.common.AttributesConstants;
import com.study.netty.firstchat.server.course16.packet.LoginRequestPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author 卫云鹏
 * @date in 11:47 2020/1/7
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
        String userName = msg.getUserName();
        if(userName != null){
            System.out.println("服务端收到【"+userName+"】发送的登录请求。。。");
            ctx.channel().attr(AttributesConstants.LOGIN).set(true);
        }else{
            System.out.println("发送的登录请求数据错误。。。");
        }
    }
}
