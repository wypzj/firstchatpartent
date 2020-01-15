package com.study.netty.firstchat.server.course18.packet;

import com.study.netty.firstchat.server.course18.common.Commands;
import com.study.netty.firstchat.server.course18.packet.inter.AbstractPacket;

/**
 * @author a small asshole
 * @version 1.0
 * @description 客户端心跳包数据请求实体类
 * @date in 16:37 2020/1/15
 * @since 1.0
 */
public class HeartBeatRequestPacket extends AbstractPacket {
    @Override
    public byte getCommand() {
        return Commands.HEART_BEAT_REQUEST;
    }
}
