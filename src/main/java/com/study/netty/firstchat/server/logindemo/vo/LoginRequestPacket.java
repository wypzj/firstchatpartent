package com.study.netty.firstchat.server.logindemo.vo;

import com.study.netty.firstchat.server.logindemo.vo.commands.Command;
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

    private String isSuccess;

    private String message;

    @Override
    public Byte getCommand() {
        return Command.LOGING_COMMAND;
    }
}
