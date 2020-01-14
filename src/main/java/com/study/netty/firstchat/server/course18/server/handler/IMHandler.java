package com.study.netty.firstchat.server.course18.server.handler;

import com.study.netty.firstchat.server.course18.common.Commands;
import com.study.netty.firstchat.server.course18.packet.inter.AbstractPacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author a small asshole
 * @version 1.0
 * @description handler控制类
 * @date in 19:33 2020/1/14
 * @since 1.0
 */
@ChannelHandler.Sharable
public class IMHandler extends SimpleChannelInboundHandler<AbstractPacket> {
    public static final IMHandler INSTANCE = new IMHandler();

    private static Map<Byte, SimpleChannelInboundHandler<? extends AbstractPacket>> handlerMap;

    private IMHandler() {
        handlerMap = new HashMap<>(16);
        handlerMap.put(Commands.CREATE_GROUP_REQUEST, CreateGroupRequestHandler.INSTANCE);
        handlerMap.put(Commands.JOIN_CROUP_REQUEST, JoinGroupRequestHandler.INSTANCE);
        handlerMap.put(Commands.LIST_GROUP_MEMBER_REQUEST, ListGroupMembersRequestHandler.INSTANCE);
        handlerMap.put(Commands.QUIT_GROUP_REQUEST, QuitGroupRequestHandler.INSTANCE);
        handlerMap.put(Commands.MESSAGE_REQUEST, MessageHandler.INSTANCE);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AbstractPacket msg) throws Exception {
        handlerMap.get(msg.getCommand()).channelRead(ctx, msg);
    }
}
