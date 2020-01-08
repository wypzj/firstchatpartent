package com.study.netty.firstchat.server.course16.common;

/**
 * @author 卫云鹏
 * @date in 19:06 2020/1/6
 */
public class Commands {
    /**
     * 登录请求指令
     */
    public static final byte LOGIN_REQUEST = 1;
    /**
     * 登录响应指令
     */
    public static final byte LOGIN_RESPONSE = 2;
    /**
     * 消息请求指令
     */
    public static final byte MESSAGE_REQUEST = 3;
    /**
     * 消息响应指令
     */
    public static final byte MESSAGE_RESPONSE = 4;

    /**
     * 创建群组请求指令
     */
    public static final byte CREATE_GROUP_REQUEST = 5;

    /**
     * 创建群组响应指令
     */
    public static final byte CREATE_GROUP_RESPONSE = 6;
}
