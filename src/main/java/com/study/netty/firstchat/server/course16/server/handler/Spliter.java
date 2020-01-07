package com.study.netty.firstchat.server.course16.server.handler;

import com.study.netty.firstchat.server.course16.protocol.ProtocolCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * 针对当前协议自定义的拆包器
 * <p>
 * 由于LengthFieldBasedFrameDecoder没有无参构造函数，所以自定义的拆包器必须
 * 显式的声明一个有参构造，覆盖默认无参构造中super调用父类的无参构造
 *
 * @author 卫云鹏
 * @date in 12:25 2020/1/7
 */
public class Spliter extends LengthFieldBasedFrameDecoder {

    /**
     * 数据长度域在协议中的偏移量
     */
    private static final int LENGTHFIELDOFFSET = 7;
    /**
     * 数据长度域占用字节数
     */
    private static final int LENGTHFIELDLENGTH = 4;

    /**
     * 自定义的拆包类
     * 实质：调用父类LengthFieldBasedFrameDecoder的有参构造完成的拆包工作
     */
    public Spliter() {
        super(Integer.MAX_VALUE, LENGTHFIELDOFFSET, LENGTHFIELDLENGTH);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        //QUESTION:ByteBuf是一个channel只有有个，通过读写指针的移动去设置可读可写区域
        //readInt 会读取32bit的Integer类型
        if (in.getInt(in.readerIndex()) != ProtocolCodec.MAGIC_NUMBER) {
            System.out.println("当前消息使用的协议不是当前使用的协议，连接将会断开！");
            ctx.channel().close();
            return null;
        }
        return super.decode(ctx, in);
    }
}
