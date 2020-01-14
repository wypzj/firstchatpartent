package com.study.netty.firstchat.server.course18.client.handler;

import com.study.netty.firstchat.server.course18.packet.LoginResponsePacket;
import com.study.netty.firstchat.server.course18.util.Session;
import com.study.netty.firstchat.server.course18.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author 卫云鹏
 * @date in 11:26 2020/1/7
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) throws Exception {
        boolean loginTig = msg.isLoginTig();
        if (loginTig){
            Session session = new Session();
            session.setUserId(msg.getUserId());
            session.setUserName(msg.getUserName());
            SessionUtil.bindSession(session,ctx.channel());
            System.out.println("【"+msg.getUserId()+"】:"+msg.getUserName()+"登录成功");
        }else{
            System.out.println("登录失败！");
        }
    }
}