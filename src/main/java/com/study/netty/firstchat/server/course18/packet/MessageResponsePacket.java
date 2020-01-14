package com.study.netty.firstchat.server.course18.packet;

import com.study.netty.firstchat.server.course18.common.Commands;
import com.study.netty.firstchat.server.course18.packet.inter.AbstractPacket;
import com.study.netty.firstchat.server.course18.util.Session;
import lombok.*;

/**
 * @author a small asshole
 * @version 1.0
 * @description 消息响应数据实体类
 * @date in 15:45 2020/1/14
 * @since 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MessageResponsePacket extends AbstractPacket {
    private Session fromUser;
    private String groupId;
    private String message;

    @Override
    public byte getCommand() {
        return Commands.MESSAGE_RESPONSE;
    }
}
