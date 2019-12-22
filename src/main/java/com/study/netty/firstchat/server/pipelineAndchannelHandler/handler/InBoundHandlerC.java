package com.study.netty.firstchat.server.pipelineAndchannelHandler.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 卫云鹏
 * @date in 11:24 2019/12/16
 */
@Slf4j
public class InBoundHandlerC extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("inbound:C");
        super.channelRead(ctx,msg);
    }
}
