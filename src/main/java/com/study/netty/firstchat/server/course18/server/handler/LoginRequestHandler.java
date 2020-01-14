package com.study.netty.firstchat.server.course18.server.handler;

import com.study.netty.firstchat.server.course18.common.AttributesConstants;
import com.study.netty.firstchat.server.course18.packet.LoginRequestPacket;
import com.study.netty.firstchat.server.course18.packet.LoginResponsePacket;
import com.study.netty.firstchat.server.course18.util.Session;
import com.study.netty.firstchat.server.course18.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * @author 卫云鹏
 * @date in 11:47 2020/1/7
 */
@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();

    private LoginRequestHandler(){}

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

