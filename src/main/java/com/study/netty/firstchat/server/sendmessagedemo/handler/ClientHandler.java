package com.study.netty.firstchat.server.sendmessagedemo.handler;

import com.study.netty.firstchat.server.sendmessagedemo.pojo.response.MessageResponsePacket;
import com.study.netty.firstchat.server.sendmessagedemo.protocol.PacketCodeC;
import com.study.netty.firstchat.server.sendmessagedemo.pojo.request.LoginRequestPacket;
import com.study.netty.firstchat.server.sendmessagedemo.pojo.abstractpojo.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 卫云鹏
 * @date in 11:24 2019/12/16
 */
@Slf4j
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("客户端开始登录");
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(Long.MAX_VALUE);
        loginRequestPacket.setUsername("威先森");
        loginRequestPacket.setPassword("iooi");
        ByteBuf encode = PacketCodeC.INSTANCE.encode(loginRequestPacket);
        ctx.channel().writeAndFlush(encode);
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        //解码
        Packet decode = PacketCodeC.INSTANCE.decode(byteBuf);
        if(decode instanceof LoginRequestPacket){
            LoginRequestPacket packet = (LoginRequestPacket) decode;
            log.info("客户端接受到消息");
            log.info("是否登录成功:{}",packet.getIsSuccess());
            log.info("登录响应:{}",packet.getMessage());
        }else if(decode instanceof MessageResponsePacket){
            MessageResponsePacket messageResponsePacket = (MessageResponsePacket) decode;
            System.out.println("服务端:"+messageResponsePacket.getMessage());
        }
    }
}
