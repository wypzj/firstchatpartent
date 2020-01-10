package com.study.netty.firstchat.server.course17.client.console;

import com.study.netty.firstchat.server.course17.client.console.inter.ConsoleCommandInter;
import com.study.netty.firstchat.server.course17.packet.JoinGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 加入群聊的命令控制
 * @author a small asshole
 * @date in 15:30 2020/1/10
 * @since 1.0
 */
public class JoinGroupConsoleCommand implements ConsoleCommandInter {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("输入groupId，加入群聊：");
        String groupId = scanner.next();
        JoinGroupRequestPacket requestPacket = new JoinGroupRequestPacket();
        requestPacket.setGroupId(groupId);

        channel.writeAndFlush(requestPacket);
    }
}
