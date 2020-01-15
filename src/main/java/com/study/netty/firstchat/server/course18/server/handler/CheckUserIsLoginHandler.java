package com.study.netty.firstchat.server.course18.server.handler;

import com.study.netty.firstchat.server.course18.common.AttributesConstants;
import com.study.netty.firstchat.server.course18.packet.HeartBeatRequestPacket;
import com.study.netty.firstchat.server.course18.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 效验当前用户是否已经登录的handler
 * 已经登录：移除掉这个handler
 * 未登录：关闭当前连接channel（产线上不应该直接关闭连接，会做特殊处理）
 * @author 卫云鹏
 * @date in 14:40 2020/1/7
 */
@ChannelHandler.Sharable
public class CheckUserIsLoginHandler extends ChannelInboundHandlerAdapter {
    public static final CheckUserIsLoginHandler INSTANCE = new CheckUserIsLoginHandler();

    private CheckUserIsLoginHandler(){}
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(!(msg instanceof HeartBeatRequestPacket)){
            if(!SessionUtil.checkIsLogin(ctx.channel())){
                ctx.channel().close();
            }else{
                ctx.channel().pipeline().remove(CheckUserIsLoginHandler.class);
                super.channelRead(ctx, msg);
            }
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        if(ctx.channel().attr(AttributesConstants.LOGIN).get() == null || !ctx.channel().attr(AttributesConstants.LOGIN).get()){
            System.out.println("当前用户未登录，连接关闭！");
        }else{
            System.out.println("当前用户已经登录，移除登录效验的handler");
        }
    }
}
