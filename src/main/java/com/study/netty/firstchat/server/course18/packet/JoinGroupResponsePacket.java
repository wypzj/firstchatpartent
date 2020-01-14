package com.study.netty.firstchat.server.course18.packet;

import com.study.netty.firstchat.server.course18.common.Commands;
import com.study.netty.firstchat.server.course18.packet.inter.AbstractPacket;
import lombok.*;

/**
 * @author a small asshole
 * @version 1.0
 * @description 加入群组响应数据类
 * @date 2020/1/11
 * @since 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JoinGroupResponsePacket extends AbstractPacket {
    /**
     * 返回结果信息
     */
    private String resultMessage;

    @Override
    public byte getCommand() {
        return Commands.JOIN_GROUP_RESPONSE;
    }
}
