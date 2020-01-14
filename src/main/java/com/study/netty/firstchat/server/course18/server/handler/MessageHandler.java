package com.study.netty.firstchat.server.course18.server.handler;

import com.study.netty.firstchat.server.course18.packet.MessageRequestPacket;
import com.study.netty.firstchat.server.course18.packet.MessageResponsePacket;
import com.study.netty.firstchat.server.course18.util.GroupUtil;
import com.study.netty.firstchat.server.course18.util.Session;
import com.study.netty.firstchat.server.course18.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @author 卫云鹏
 * @date in 11:32 2020/1/7
 */
@ChannelHandler.Sharable
public class MessageHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    public static final MessageHandler INSTANCE = new MessageHandler();

    private MessageHandler(){}
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {
        String groupId = msg.getGroupId();
        Session session = SessionUtil.getSession(ctx.channel());
        ChannelGroup channelGroup = GroupUtil.getChannelGroup(groupId);

        //构造服务端响应
        MessageResponsePacket responsePacket = new MessageResponsePacket();
        responsePacket.setMessage(msg.getMessage());
        responsePacket.setFromUser(session);
        responsePacket.setGroupId(groupId);
        //写出
        channelGroup.writeAndFlush(responsePacket);
    }
}
