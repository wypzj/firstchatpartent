package com.study.netty.firstchat.server.course18.packet;


import com.study.netty.firstchat.server.course18.common.Commands;
import com.study.netty.firstchat.server.course18.packet.inter.AbstractPacket;
import lombok.*;

/**
 * 加入群聊请求实体类
 * @author a small asshole
 * @date in 16:49 2020/1/10
 * @since 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JoinGroupRequestPacket extends AbstractPacket {
    /**
     * 群组id
     */
    private String groupId;
    @Override
    public byte getCommand() {
        return Commands.JOIN_CROUP_REQUEST;
    }
}
