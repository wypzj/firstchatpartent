package com.study.netty.firstchat.server.sendmessagedemo.pojo.response;

import com.study.netty.firstchat.server.sendmessagedemo.constants.Command;
import com.study.netty.firstchat.server.sendmessagedemo.pojo.abstractpojo.Packet;
import lombok.Getter;
import lombok.Setter;

/**
 * 消息响应的协议包实体
 * @author 卫云鹏
 * @date in 12:44 2019/12/18
 */
@Getter
@Setter
public class MessageResponsePacket extends Packet {
    private String message;
    @Override
    public Byte getCommand() {
        return Command.MESSAGERESPONSE_COMMAND;
    }
}
