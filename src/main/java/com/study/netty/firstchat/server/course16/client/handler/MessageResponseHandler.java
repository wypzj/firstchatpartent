package com.study.netty.firstchat.server.course16.client.handler;

import com.study.netty.firstchat.server.course16.packet.MessagePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author 卫云鹏
 * @date in 11:32 2020/1/7
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessagePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessagePacket msg) throws Exception {
        System.out.println("客户端对服务端推送消息的处理。。。");
    }
}
