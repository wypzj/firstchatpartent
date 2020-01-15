package com.study.netty.firstchat.server.course18.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @author a small asshole
 * @version 1.0
 * @description TODO
 * @date in 18:26 2020/1/15
 * @since TODO
 */
public class TestOutBoundHandler extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("outBoundHandler");
        super.write(ctx, msg, promise);
    }
}
