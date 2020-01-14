package com.study.netty.firstchat.server.course18.client.handler;

import com.study.netty.firstchat.server.course18.packet.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author 卫云鹏
 * @date in 11:32 2020/1/7
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket msg) throws Exception {
        StringBuilder builder = new StringBuilder();
        builder.append("收到群[")
                .append(msg.getGroupId())
                .append("]中[")
                .append(msg.getFromUser().getUserId())
                .append(":")
                .append(msg.getFromUser().getUserName())
                .append("]发来的消息：")
                .append(msg.getMessage());
        System.out.println(builder.toString());
    }
}
