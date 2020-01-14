package com.study.netty.firstchat.server.course18.client.handler;

import com.study.netty.firstchat.server.course18.packet.ListMembersGroupResponsePacket;
import com.study.netty.firstchat.server.course18.util.Session;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;

/**
 * @author a small asshole
 * @version 1.0
 * @description 查询群组人员响应处理handler
 * @date in 14:25 2020/1/14
 * @since 1.0
 */
public class ListMembersGroupResponseHandler extends SimpleChannelInboundHandler<ListMembersGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListMembersGroupResponsePacket msg) throws Exception {
        StringBuilder builder = new StringBuilder();
        builder.append("当前群组中成员包括：[");
        List<Session> groupMemberList = msg.getGroupMemberList();
        for (Session session : groupMemberList) {
            builder.append(session.getUserId()).append(":").append(session.getUserName()).append(",");
        }
        builder.substring(0,builder.length()-1);
        builder.append("]");
        System.out.println(builder.toString());
    }
}
