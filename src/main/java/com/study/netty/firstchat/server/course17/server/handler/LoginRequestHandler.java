package com.study.netty.firstchat.server.course17.server.handler;

import com.study.netty.firstchat.server.course17.common.AttributesConstants;
import com.study.netty.firstchat.server.course17.packet.LoginRequestPacket;
import com.study.netty.firstchat.server.course17.packet.LoginResponsePacket;
import com.study.netty.firstchat.server.course17.util.Session;
import com.study.netty.firstchat.server.course17.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * @author 卫云鹏
 * @date in 11:47 2020/1/7
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
        String userName = msg.getUserName();
        String userId = UUID.randomUUID().toString();
        if (userName != null) {
            System.out.println("服务端收到【" + userName + "】发送的登录请求。。。");
            Session session = new Session();
            session.setUserId(userId);
            session.setUserName(userName);
            SessionUtil.bindSession(session, ctx.channel());
            ctx.channel().attr(AttributesConstants.LOGIN).set(true);
            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            loginResponsePacket.setUserId(userId);
            loginResponsePacket.setUserName(userName);
            loginResponsePacket.setLoginTig(true);
            ctx.channel().writeAndFlush(loginResponsePacket);
        } else {
            System.out.println("发送的登录请求数据错误。。。");
        }
    }
}
