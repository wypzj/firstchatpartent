package com.study.netty.firstchat.server.course17.server.handler;

import com.study.netty.firstchat.server.course17.packet.JoinGroupRequestPacket;
import com.study.netty.firstchat.server.course17.packet.JoinGroupResponsePacket;
import com.study.netty.firstchat.server.course17.util.GroupUtil;
import com.study.netty.firstchat.server.course17.util.Session;
import com.study.netty.firstchat.server.course17.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.ArrayList;
import java.util.List;

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
        List<String> memberIdList = new ArrayList<>(1);
        memberIdList.add(userId);
        //userId绑定群组
        ChannelGroup channelGroup = GroupUtil.getChannelGroup(groupId);
        GroupUtil.bindGroupMembers(groupId, memberIdList, channelGroup);

        //channel绑定channelGroup
        channelGroup.add(channel);

        //封装packet响应
        JoinGroupResponsePacket responsePacket = new JoinGroupResponsePacket();
        responsePacket.setResultMessage("加入群组["+groupId+"]成功！");
        channel.writeAndFlush(responsePacket);
    }
}
