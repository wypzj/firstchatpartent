package com.study.netty.firstchat.server.sendmessagedemo.improvehandler;

import com.study.netty.firstchat.server.sendmessagedemo.constants.Attributes;
import com.study.netty.firstchat.server.sendmessagedemo.pojo.request.MessageRequestPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 卫云鹏
 * @date in 23:29 2019/12/23
 */
@Slf4j
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {
        System.out.println("客户端收到服务端发送的消息："+msg.getMessage());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }
}
