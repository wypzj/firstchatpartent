package com.study.netty.firstchat.server.course18.client.handler;

import com.study.netty.firstchat.server.course18.packet.HeartBeatRequestPacket;
import com.study.netty.firstchat.server.course18.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.TimeUnit;

/**
 * @author a small asshole
 * @version 1.0
 * @description 客户端发送心跳请求handler
 * @date in 16:25 2020/1/15
 * @since 1.0
 */
@ChannelHandler.Sharable
public class HeartBeatTimerHandler extends ChannelInboundHandlerAdapter {
    public static final HeartBeatTimerHandler INSTANCE = new HeartBeatTimerHandler();

    private HeartBeatTimerHandler() {
    }

    /**
     * 发送心跳包的间隔
     */
    private static final long SEND_HEART_BEAT_PERIOD = 5;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //定时调用发送心跳包以保持跟服务端的连接
        scheduleSendHeartBeat(ctx);
        super.channelActive(ctx);
    }

    private void scheduleSendHeartBeat(ChannelHandlerContext ctx) {
        ctx.executor().scheduleAtFixedRate(() -> {
            if (ctx.channel().isActive()) {
                System.out.println("客户端发送心跳包");
                ctx.writeAndFlush(new HeartBeatRequestPacket());
            }
        }, SEND_HEART_BEAT_PERIOD, SEND_HEART_BEAT_PERIOD, TimeUnit.SECONDS);
    }
}
