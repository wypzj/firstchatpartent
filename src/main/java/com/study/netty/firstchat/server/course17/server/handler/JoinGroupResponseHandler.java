package com.study.netty.firstchat.server.course17.server.handler;

import com.study.netty.firstchat.server.course17.packet.JoinGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author a small asshole
 * @version 1.0
 * @description 加入群聊响应handler
 * @date 2020/1/11
 * @since 1.0
 */
public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, JoinGroupResponsePacket joinGroupResponsePacket) throws Exception {
        System.out.println(joinGroupResponsePacket.getResultMessage());
    }
}
