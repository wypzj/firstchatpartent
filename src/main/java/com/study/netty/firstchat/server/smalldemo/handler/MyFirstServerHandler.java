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
public class MyFirstServerHandler extends ChannelInboundHandlerAdapter {

//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        log.info("客户端出数据");
//        //1.获取数据
//        ByteBuf buffer = getByteBuf(ctx);
//        //2.写出数据
//        ctx.writeAndFlush(buffer);
//    }
//
//    private ByteBuf getByteBuf(ChannelHandlerContext ctx){
//        //1.获取netty对二进制的抽象ByteBuf
//        ByteBuf buffer = ctx.alloc().buffer();
//        //2.准备要发生的数据
//        byte[] bytes = "你好，netty".getBytes(Charset.forName("UTF-8"));
//        //3.将数据写入ByteBuf
//        buffer.writeBytes(bytes);
//        return buffer;
//    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf message = (ByteBuf) msg;
        log.info("收到客户端发送的消息:{}", message.toString(Charset.forName("UTF-8")));
        ByteBuf byteBuf = ctx.alloc().buffer();
        String sendMessage = "编号：9527，收到收到，over";
        byte[] bytes = sendMessage.getBytes(Charset.forName("UTF-8"));
        log.info("服务器端发送消息:{}", sendMessage);
        ctx.channel().writeAndFlush(byteBuf.writeBytes(bytes));
    }
}
