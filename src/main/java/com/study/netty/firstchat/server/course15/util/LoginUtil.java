package com.study.netty.firstchat.server.course15.util;

import com.study.netty.firstchat.server.course15.constants.Attributes;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;

/**
 * @author 卫云鹏
 * @date in 13:55 2020/1/3
 */
public class LoginUtil {

    /**
     * 效验登录状态
     * @param ctx
     * @return
     */
    public static boolean checkIsLogin(ChannelHandlerContext ctx){
        return ctx.channel().attr(Attributes.LOGIN).get() != null;
    }
}
