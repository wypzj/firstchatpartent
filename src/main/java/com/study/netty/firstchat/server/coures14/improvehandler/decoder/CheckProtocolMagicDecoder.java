package com.study.netty.firstchat.server.coures14.improvehandler.decoder;

import com.study.netty.firstchat.server.coures14.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @author 卫云鹏
 * @date in 10:12 2019/12/31
 */
public class CheckProtocolMagicDecoder extends LengthFieldBasedFrameDecoder {
    /**
     * 数据
     */
    private static final Integer LENGTH_FIELD_OFFSET = 7;
    private static final Integer LENGTH_FIELD_LENGTH = 4;


    public CheckProtocolMagicDecoder() {
        super(Integer.MAX_VALUE, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        int magicNumIndex = in.readerIndex();
        if (in.getInt(magicNumIndex) != PacketCodeC.MAGIC_NUMBER) {
            System.out.println("不是定义的协议，连接关闭!!");
            ctx.channel().close();
            return null;
        }
        return super.decode(ctx, in);
    }
}
