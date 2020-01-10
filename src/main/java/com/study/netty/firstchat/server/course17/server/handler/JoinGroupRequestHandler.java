package com.study.netty.firstchat.server.course17.server.handler;

import com.study.netty.firstchat.server.course17.packet.JoinGroupRequestPacket;
import com.study.netty.firstchat.server.course17.util.GroupUtil;
import com.study.netty.firstchat.server.course17.util.Session;
import com.study.netty.firstchat.server.course17.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @author a small asshole
 * @version 1.0
 * @description 加入群组请求处理handler
 * @date in 17:44 2020/1/10
 * @since 1.0
 */
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket msg) throws Exception {
        Channel channel = ctx.channel();
        String groupId = msg.getGroupId();

        Session session = SessionUtil.getSession(channel);
        String userId = session.getUserId();
        String userName = session.getUserName();
        //userId绑定群组


        ChannelGroup channelGroup = GroupUtil.getChannelGroup(groupId);
        //channel绑定channelGroup
        channelGroup.add(channel);

    }
}
