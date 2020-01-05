package com.study.netty.firstchat.server.course15.improvehandler;

import com.study.netty.firstchat.server.course15.pojo.request.MessageRequestPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 客户端消息handler
 * @author 卫云鹏
 * @date in 23:29 2019/12/23
 */
@Slf4j
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {
        String fromUserId = msg.getFromUserId();
        String fromUserName = msg.getFromUserName();
        System.out.println("【"+fromUserId+":"+fromUserName+"】-->"+msg.getMessage());
    }
}
