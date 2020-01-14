package com.study.netty.firstchat.server.course18.packet;

import com.study.netty.firstchat.server.course18.common.Commands;
import com.study.netty.firstchat.server.course18.packet.inter.AbstractPacket;
import lombok.*;

/**
 * 群组消息请求数据实体类
 * @author 卫云鹏
 * @date in 19:23 2020/1/6
 * @since 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MessageRequestPacket extends AbstractPacket {
    /**
     * 群组id
     */
    private String groupId;

    /**
     * 消息体
     */
    private String message;
    @Override
    public byte getCommand() {
        return Commands.MESSAGE_REQUEST;
    }
}
