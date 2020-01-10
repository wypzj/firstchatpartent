package com.study.netty.firstchat.server.course17.commonhandler;

import com.study.netty.firstchat.server.course17.packet.inter.AbstractPacket;
import com.study.netty.firstchat.server.course17.protocol.ProtocolCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 编码handler
 * @author 卫云鹏
 * @date in 10:39 2020/1/7
 */
public class PacketEncoder extends MessageToByteEncoder<AbstractPacket> {
    @Override
    protected void encode(ChannelHandlerContext ctx, AbstractPacket msg, ByteBuf out) throws Exception {
        ProtocolCodec.INSTANCE.encode(out,msg);
    }
}
