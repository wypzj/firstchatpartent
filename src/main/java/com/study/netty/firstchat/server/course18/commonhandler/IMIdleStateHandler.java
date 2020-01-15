package com.study.netty.firstchat.server.course18.commonhandler;

import com.study.netty.firstchat.server.course18.packet.HeartBeatRequestPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author a small asshole
 * @version 1.0
 * @description 服务端空闲检测
 * @date in 16:05 2020/1/15
 * @since 1.0
 */
public class IMIdleStateHandler extends IdleStateHandler {
    /**
     * 空闲检测间隔
     */
    private static final long READERIDLE_TIME = 15;

    public IMIdleStateHandler(){
        super(READERIDLE_TIME,0,0, TimeUnit.SECONDS);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        System.out.println("当前客户端在"+READERIDLE_TIME+"s内没有发送请求信息，断开连接");
        ctx.channel().close();
    }
}
