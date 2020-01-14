package com.study.netty.firstchat.server.course18.server.handler;

import com.study.netty.firstchat.server.course18.packet.ListMembersGroupRequestPacket;
import com.study.netty.firstchat.server.course18.packet.ListMembersGroupResponsePacket;
import com.study.netty.firstchat.server.course18.util.GroupUtil;
import com.study.netty.firstchat.server.course18.util.Session;
import com.study.netty.firstchat.server.course18.util.SessionUtil;
import io.netty.channel.ChannelHandler;
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
@ChannelHandler.Sharable
public class ListGroupMembersRequestHandler extends SimpleChannelInboundHandler<ListMembersGroupRequestPacket> {
    public static final ListGroupMembersRequestHandler INSTANCE = new ListGroupMembersRequestHandler();

    private ListGroupMembersRequestHandler(){}
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
