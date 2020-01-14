package com.study.netty.firstchat.server.course18.server.handler;

import com.study.netty.firstchat.server.course18.packet.QuitGroupRequestPacket;
import com.study.netty.firstchat.server.course18.packet.QuitGroupResponsePacket;
import com.study.netty.firstchat.server.course18.util.GroupUtil;
import com.study.netty.firstchat.server.course18.util.Session;
import com.study.netty.firstchat.server.course18.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
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
@ChannelHandler.Sharable
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {
    public static final QuitGroupRequestHandler INSTANCE = new QuitGroupRequestHandler();

    private QuitGroupRequestHandler(){}
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
