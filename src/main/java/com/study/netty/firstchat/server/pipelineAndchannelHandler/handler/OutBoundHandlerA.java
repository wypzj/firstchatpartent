package com.study.netty.firstchat.server.pipelineAndchannelHandler.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 卫云鹏
 * @date in 11:24 2019/12/16
 */
@Slf4j
public class OutBoundHandlerA extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("outBound:A");
        super.write(ctx, msg, promise);
    }
}
