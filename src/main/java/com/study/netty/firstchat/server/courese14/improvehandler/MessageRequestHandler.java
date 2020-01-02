package com.study.netty.firstchat.server.courese14.improvehandler;

import com.study.netty.firstchat.server.courese14.pojo.request.MessageRequestPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 卫云鹏
 * @date in 23:29 2019/12/23
 */
@Slf4j
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {
        //接受消息逻辑处理
        String message = msg.getMessage();
        System.out.println("服务端收到消息:" + message);
    }
}
