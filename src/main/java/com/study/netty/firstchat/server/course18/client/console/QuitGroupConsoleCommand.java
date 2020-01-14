package com.study.netty.firstchat.server.course18.client.console;

import com.study.netty.firstchat.server.course18.client.console.inter.ConsoleCommandInter;
import com.study.netty.firstchat.server.course18.packet.QuitGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author a small asshole
 * @version 1.0
 * @description 退出群聊命令执行类
 * @date 2020/1/11
 * @since 1.0
 */
public class QuitGroupConsoleCommand implements ConsoleCommandInter {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("输入groupId，退出群聊：");
        String groupId = scanner.next();

        //构建退出群聊的请求
        QuitGroupRequestPacket quitGroupRequestPacket = new QuitGroupRequestPacket();
        quitGroupRequestPacket.setGroupId(groupId);

        channel.writeAndFlush(quitGroupRequestPacket);
    }
}
