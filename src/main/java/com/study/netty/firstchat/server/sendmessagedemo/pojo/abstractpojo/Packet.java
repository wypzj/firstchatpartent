package com.study.netty.firstchat.server.sendmessagedemo.pojo.abstractpojo;

import lombok.Getter;

/**
 * 请求的数据包抽象类
 * 在这个类中可以查看到数据包中的command
 * @author 卫云鹏
 * @date in 13:54 2019/12/17
 */
@Getter
public abstract class Packet {
    /**
     * 程序当前的协议版本
     */
    private Byte version = 1;

    /**
     * 获取数据包中的Byet字节
     * @return 指令字节
     */
    public abstract Byte getCommand();
}
