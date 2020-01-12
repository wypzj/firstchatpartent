package com.study.netty.firstchat.server.course17.common;

/**
 * @author 卫云鹏
 * @date in 19:06 2020/1/6
 */
public class Commands {
    /**
     * 登录【请求】指令
     */
    public static final byte LOGIN_REQUEST = 1;
    /**
     * 登录【响应】指令
     */
    public static final byte LOGIN_RESPONSE = 2;
    /**
     * 消息【请求】指令
     */
    public static final byte MESSAGE_REQUEST = 3;
    /**
     * 消息【响应】指令
     */
    public static final byte MESSAGE_RESPONSE = 4;

    /**
     * 创建群组【请求】指令
     */
    public static final byte CREATE_GROUP_REQUEST = 5;

    /**
     * 创建群组【响应】指令
     */
    public static final byte CREATE_GROUP_RESPONSE = 6;

    /**
     * 加入群组【请求】指令
     */
    public static final byte JOIN_CROUP_REQUEST = 7;

    /**
     * 加入群组【响应】指令
     */
    public static final byte JOIN_GROUP_RESPONSE = 8;

    /**
     * 退出群组【请求】指令
     */
    public static final byte QUIT_GROUP_REQUEST = 9;

    /**
     * 退出群组【响应】指令
     */
    public static final byte QUIT_GROUP_RESPONSE = 10;
}
