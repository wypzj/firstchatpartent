package com.study.netty.firstchat.server.course17.packet;

import com.study.netty.firstchat.server.course17.common.Commands;
import com.study.netty.firstchat.server.course17.packet.inter.AbstractPacket;

/**
 * @author 卫云鹏
 * @date in 19:23 2020/1/6
 */
public class MessagePacket extends AbstractPacket {
    /**
     * 消息接收人id
     */
    private String toUserId;

    /**
     * 消息发送人id
     */
    private String fromeUserId;

    /**
     * 消息体
     */
    private String message;
    @Override
    public byte getCommand() {
        return Commands.MESSAGE_REQUEST;
    }
}
