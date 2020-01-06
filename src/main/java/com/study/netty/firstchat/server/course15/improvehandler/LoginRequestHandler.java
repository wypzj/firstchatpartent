package com.study.netty.firstchat.server.course15.improvehandler;

import com.study.netty.firstchat.server.course15.pojo.request.LoginRequestPacket;
import com.study.netty.firstchat.server.course15.util.Session;
import com.study.netty.firstchat.server.course15.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * 服务端登录请求handler
 * @author 卫云鹏
 * @date in 23:22 2019/12/23
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
        //登录逻辑
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        System.out.println("接受到客户端发送的登录请求");
        if(msg.getUsername() != null){
            //设置登录状态
            Session session = new Session();
            //16位userid
            String userId = UUID.randomUUID().toString();
            session.setUserId(userId);
            session.setUserName(msg.getUsername());
            SessionUtil.bindSession(session,ctx.channel());
            loginRequestPacket.setUsername(msg.getUsername());
            loginRequestPacket.setUserId(userId);
            loginRequestPacket.setTig(true);
            loginRequestPacket.setMessage("userId:【"+userId+"】,用户：【"+msg.getUsername()+"】登录成功");
        }else{
            loginRequestPacket.setTig(false);
            loginRequestPacket.setMessage("用户名为空，登录失败");
        }
        ctx.channel().writeAndFlush(loginRequestPacket);
    }
}
