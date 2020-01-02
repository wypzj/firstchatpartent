package com.study.netty.firstchat.server.course15.pojo.request;

import com.study.netty.firstchat.server.course15.constants.Command;
import com.study.netty.firstchat.server.course15.pojo.abstractpojo.Packet;
import lombok.Data;

/**
 * 登录请求数据包实体
 * @author 卫云鹏
 * @date in 15:35 2019/12/17
 */
@Data
public class LoginRequestPacket extends Packet {
    private Long userId;

    private String username;

    private String password;

    private Boolean tig;

    private String message;

    @Override
    public Byte getCommand() {
        return Command.LOGING_COMMAND;
    }
}
