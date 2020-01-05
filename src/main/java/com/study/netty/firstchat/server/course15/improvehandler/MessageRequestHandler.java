package com.study.netty.firstchat.server.course15.improvehandler;

import com.study.netty.firstchat.server.course15.pojo.request.MessageRequestPacket;
import com.study.netty.firstchat.server.course15.util.Session;
import com.study.netty.firstchat.server.course15.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 服务端消息handler
 * @author 卫云鹏
 * @date in 23:29 2019/12/23
 */
@Slf4j
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {
        //接受消息逻辑处理
        //1.拿到消息发送方的session信息
        Session session = SessionUtil.getSession(ctx.channel());
        if(session == null){
            System.out.println("程序出错，用户未登录，不能发送消息");
            return;
        }
        //2.拿到消息接收方的session信息
        String toUserId = msg.getToUserId();

        Channel channel = SessionUtil.getChannel(toUserId);

        //3.转发消息
        String message = msg.getMessage();

        msg.setFromUserId(session.getUserId());
        msg.setFromUserName(session.getUserName());
        System.out.println("服务端收到消息:" + message+"，服务端转发消息");
        channel.pipeline().writeAndFlush(msg);
    }
}
