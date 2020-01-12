package com.study.netty.firstchat.server.course17.client.handler;

import com.study.netty.firstchat.server.course17.packet.QuitGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author a small asshole
 * @version 1.0
 * @description 退出群聊响应handler
 * @date 2020/1/11
 * @since 1.0
 */
public class QuitResponseHandler extends SimpleChannelInboundHandler<QuitGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, QuitGroupResponsePacket quitGroupResponsePacket) throws Exception {

    }
}
