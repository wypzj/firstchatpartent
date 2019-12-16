package com.study.netty.firstchat.server.smalldemo.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

/**
 * @author 卫云鹏
 * @date in 11:24 2019/12/16
 */
@Slf4j
public class MyFirstClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //1.获取要发送的数据
        ByteBuf buffer = getByteBuf(ctx);
        //2.写出数据
        ctx.channel().writeAndFlush(buffer);
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        //1.获取netty对二进制的抽象ByteBuf
        ByteBuf buffer = ctx.alloc().buffer();
        //2.准备要发生的数据
        String sendMessage = "你好，netty";
        byte[] bytes = sendMessage.getBytes(Charset.forName("UTF-8"));
        //3.将数据写入ByteBuf
        buffer.writeBytes(bytes);
        log.info("客户端写出数据:{}", sendMessage);
        return buffer;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("客户端接受消息,消息:{}", ((ByteBuf) msg).toString(Charset.forName("UTF-8")));
    }
}
