package com.study.netty.firstchat.server.course17.server.handler;

import com.study.netty.firstchat.server.course17.packet.ListMembersGroupRequestPacket;
import com.study.netty.firstchat.server.course17.packet.ListMembersGroupResponsePacket;
import com.study.netty.firstchat.server.course17.util.GroupUtil;
import com.study.netty.firstchat.server.course17.util.Session;
import com.study.netty.firstchat.server.course17.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author a small asshole
 * @version 1.0
 * @description 获取群组成员列表handler
 * @date in 10:11 2020/1/14
 * @since 1.0
 */
public class ListGroupMembersRequestHandler extends SimpleChannelInboundHandler<ListMembersGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListMembersGroupRequestPacket msg) throws Exception {
        //TODO 判断当前用户是否已经加入该群组
        String groupId = msg.getGroupId();
        Session masterSession = SessionUtil.getSession(ctx.channel());
        List<String> userIdList = GroupUtil.listGroupMembers(masterSession.getUserId(), groupId);
        List<Session> sessionList = new ArrayList<>(userIdList.size());
        for (String userId : userIdList) {
            Session session = SessionUtil.getSession(SessionUtil.getChannel(userId));
            sessionList.add(session);
        }
        //构造响应
        ListMembersGroupResponsePacket responsePacket = new ListMembersGroupResponsePacket();
        responsePacket.setGroupMemberList(sessionList);

        //发出
        ctx.channel().writeAndFlush(responsePacket);
    }
}
