package com.study.netty.firstchat.server.course16.server.handler;

import com.study.netty.firstchat.server.course16.packet.CreateGroupRequestPacket;
import com.study.netty.firstchat.server.course16.packet.CreateGroupResponsePacket;
import com.study.netty.firstchat.server.course16.util.Session;
import com.study.netty.firstchat.server.course16.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author a small asshole
 * @date in 11:53 2020/1/9
 */
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket msg) throws Exception {
        List<String> memberUserIds = msg.getMemberUserIds();
        List<String> memberUserNameList = new ArrayList<>(memberUserIds.size() == 0 ? 16 : memberUserIds.size() + 1);
        ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());
        for (String memberUserId : memberUserIds) {
            Channel channel = SessionUtil.getChannel(memberUserId);
            if (channel != null) {
                channelGroup.add(channel);
                memberUserNameList.add(SessionUtil.getSession(channel).getUserName());
            } else {
                System.out.println("用户【" + memberUserId + "】不在线！！！");
            }
        }

        //加入群聊创建人的channel和username
        Channel createUserChannel = ctx.channel();
        channelGroup.add(createUserChannel);
        Session session = SessionUtil.getSession(createUserChannel);
        String userName = session.getUserName();
        memberUserNameList.add(userName);
        CreateGroupResponsePacket createGroupResponsePacket = new CreateGroupResponsePacket();
        createGroupResponsePacket.setMemberNameList(memberUserNameList);
        createGroupResponsePacket.setGroupId(UUID.randomUUID().toString());
        createGroupResponsePacket.setCreateSuccess(true);
        channelGroup.writeAndFlush(createGroupResponsePacket);
    }
}
