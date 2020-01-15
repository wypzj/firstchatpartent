package com.study.netty.firstchat.server.course18.commonhandler;

import com.study.netty.firstchat.server.course18.packet.inter.AbstractPacket;
import com.study.netty.firstchat.server.course18.protocol.ProtocolCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

/**
 * @author a small asshole
 * @version 1.0
 * @description 编码/解码handler
 * @date in 10:24 2020/1/15
 * @since 1.0
 */
@ChannelHandler.Sharable
public class PacketCodeCHandler extends MessageToMessageCodec<ByteBuf, AbstractPacket> {
    public static final PacketCodeCHandler INSTANCE = new PacketCodeCHandler();

    private PacketCodeCHandler() {
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, AbstractPacket msg, List<Object> out) throws Exception {
        ByteBuf byteBuf = ctx.channel().alloc().ioBuffer();
        out.add(ProtocolCodec.INSTANCE.encode(byteBuf, msg));
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        out.add(ProtocolCodec.INSTANCE.decode(msg));
    }
}
