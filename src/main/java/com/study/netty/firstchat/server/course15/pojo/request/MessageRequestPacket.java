package com.study.netty.firstchat.server.course15.pojo.request;

import com.study.netty.firstchat.server.course15.constants.Command;
import com.study.netty.firstchat.server.course15.pojo.abstractpojo.Packet;
import lombok.Getter;
import lombok.Setter;

/**
 * 发送消息的协议包
 * @author 卫云鹏
 * @date in 12:44 2019/12/18
 */
@Getter
@Setter
public class MessageRequestPacket extends Packet {
    /**
     * 消息接受人id
     */
    private String toUserId;
    /**
     * 消息发送人id
     */
    private String fromUserId;

    /**
     * 消息发送人姓名
     */
    private String fromUserName;

    private String message;
    @Override
    public Byte getCommand() {
        return Command.MESSAGEREQUEST_COMMAND;
    }
}
