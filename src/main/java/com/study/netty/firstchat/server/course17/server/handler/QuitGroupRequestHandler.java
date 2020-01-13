package com.study.netty.firstchat.server.course17.server.handler;

import com.study.netty.firstchat.server.course17.packet.QuitGroupRequestPacket;
import com.study.netty.firstchat.server.course17.packet.QuitGroupResponsePacket;
import com.study.netty.firstchat.server.course17.util.GroupUtil;
import com.study.netty.firstchat.server.course17.util.Session;
import com.study.netty.firstchat.server.course17.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author a small asshole
 * @version 1.0
 * @description 退出群组请求处理handler
 * @date 2020/1/11
 * @since 1.0
 */
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRequestPacket quitGroupRequestPacket) throws Exception {
        Channel channel = ctx.channel();
        Session session = SessionUtil.getSession(channel);
        List<String> userIdList = new ArrayList<>();
        userIdList.add(session.getUserId());
        GroupUtil.unbindGroupMembers(quitGroupRequestPacket.getGroupId(), userIdList);

        //构建退出群组的响应
        QuitGroupResponsePacket quitGroupResponsePacket = new QuitGroupResponsePacket();
        quitGroupResponsePacket.setGroupId(quitGroupRequestPacket.getGroupId());
        quitGroupResponsePacket.setTip(true);
        ctx.channel().writeAndFlush(quitGroupResponsePacket);
    }
}
