package com.study.netty.firstchat.server.course15.improvehandler;

import com.study.netty.firstchat.server.course15.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 效验登录的handler
 * @author 卫云鹏
 * @date in 14:14 2020/1/3
 */
public class LoginCheckHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(!SessionUtil.checkIsLogin(ctx.channel())){
            ctx.channel().close();
        }else{
            //已登录情况下，移除掉这个handler，实现登录后的消息都不需要进行登录效验
            ctx.channel().pipeline().remove(this);
            super.channelRead(ctx, msg);
        }

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        if(!SessionUtil.checkIsLogin(ctx.channel())){
            System.out.println("未登录，不能发送消息，连接强制关闭");
        }else{
            System.out.println("已经登录了，发送消息不需要再做登录消息，移除登录效验的handler");
        }
    }
}
