package com.study.netty.firstchat.server.course15.improvehandler;

import com.study.netty.firstchat.server.course15.constants.Attributes;
import com.study.netty.firstchat.server.course15.pojo.request.LoginRequestPacket;
import com.study.netty.firstchat.server.course15.util.Session;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 客户端登录响应消息处理类
 * @author 卫云鹏
 * @date in 23:41 2019/12/23
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginRequestPacket>{
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
        System.out.println("客户端收到登录请求的回应......");
        System.out.println(msg.getMessage());
        System.out.println("是否已登录:"+msg.getTig());
        if(msg.getTig()){
            Session session = new Session();
            String message = msg.getMessage();
            ctx.channel().attr(Attributes.SESSION).set(session);
        }
    }

    /**
     * 客户端构造登录
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端发送登录请求.....");
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUsername("张菊");
        loginRequestPacket.setPassword("W&Z");

        ctx.channel().writeAndFlush(loginRequestPacket);
    }
}
