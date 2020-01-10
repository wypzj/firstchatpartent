package com.study.netty.firstchat.server.course17.commonhandler;

import com.study.netty.firstchat.server.course17.protocol.ProtocolCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 解码handler
 * @author 卫云鹏
 * @date in 10:43 2020/1/7
 */
public class PacketDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        out.add(ProtocolCodec.INSTANCE.decode(in));
    }
}
