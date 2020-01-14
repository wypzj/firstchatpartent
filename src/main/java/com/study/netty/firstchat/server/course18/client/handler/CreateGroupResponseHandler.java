package com.study.netty.firstchat.server.course18.client.handler;

import com.study.netty.firstchat.server.course18.packet.CreateGroupResponsePacket;
import com.study.netty.firstchat.server.course18.util.Session;
import com.study.netty.firstchat.server.course18.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 创建群组响应handler
 *
 * @author a small asshole
 * @date in 16:30 2020/1/9
 */
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket msg) throws Exception {
        Session session = SessionUtil.getSession(ctx.channel());
        String userName = session.getUserName();
        if (msg.getMemberNameList().contains(userName)) {
            msg.getMemberNameList().remove(userName);
        }
        System.out.println("群组【" + msg.getGroupId() + "】创建成功，群里成员有" + msg.getMemberNameList());
    }
}
