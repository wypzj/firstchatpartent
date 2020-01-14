package com.study.netty.firstchat.server.course18.client.console;

import com.study.netty.firstchat.server.course18.client.console.inter.ConsoleCommandInter;
import com.study.netty.firstchat.server.course18.packet.MessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author a small asshole
 * @version 1.0
 * @description 群聊发送消息命令控制类
 * @date in 15:28 2020/1/14
 * @since 1.0
 */
public class SendMessageConsoleCommand implements ConsoleCommandInter {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("在群里吱声：");
        String groupId = scanner.next();
        String message = scanner.next();

        //构建请求
        MessageRequestPacket requestPacket = new MessageRequestPacket();
        requestPacket.setGroupId(groupId);
        requestPacket.setMessage(message);

        //发出
        channel.writeAndFlush(requestPacket);
    }
}
