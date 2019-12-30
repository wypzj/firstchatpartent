package com.study.netty.firstchat.server.course13.sendmessagedemo.improvehandler;

import com.study.netty.firstchat.server.course13.sendmessagedemo.constants.Attributes;
import com.study.netty.firstchat.server.course13.sendmessagedemo.pojo.request.LoginRequestPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
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
            ctx.channel().attr(Attributes.LOGIN).set(true);
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
        loginRequestPacket.setUserId(9527L);
        loginRequestPacket.setUsername("卫云鹏");
        loginRequestPacket.setPassword("W&Z");

        ctx.channel().writeAndFlush(loginRequestPacket);
    }
}
