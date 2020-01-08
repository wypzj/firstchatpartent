package com.study.netty.firstchat.server.course16.packet;

import com.study.netty.firstchat.server.course16.common.Commands;
import com.study.netty.firstchat.server.course16.packet.inter.AbstractPacket;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateGroupRequestPacket extends AbstractPacket {
    private List<String> memberUserIds = new ArrayList<>(16);


    @Override
    public byte getCommand() {
        return Commands.CREATE_GROUP_REQUEST;
    }
}
