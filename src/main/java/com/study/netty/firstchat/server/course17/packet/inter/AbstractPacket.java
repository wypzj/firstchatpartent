package com.study.netty.firstchat.server.course17.packet.inter;

/**
 * 协议体抽象类
 * @author 卫云鹏
 * @date in 19:12 2020/1/6
 */
public abstract class AbstractPacket {
    /**
     * 当前协议版本号
     */
    public static final byte VERSION = 1;

    /**
     * 获取当前协议中的指令
     * @return 代表当前协议指令的byte字节
     */
    public abstract byte getCommand();
}
