package com.study.netty.firstchat.server.course15.improvehandler;

import com.study.netty.firstchat.server.course15.pojo.abstractpojo.Packet;
import com.study.netty.firstchat.server.course15.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 协议编码器
 * @author 卫云鹏
 * @date in 23:41 2019/12/23
 */
public class PacketEncoder extends MessageToByteEncoder<Packet> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
        PacketCodeC.INSTANCE.encode(msg,out);
    }
}
