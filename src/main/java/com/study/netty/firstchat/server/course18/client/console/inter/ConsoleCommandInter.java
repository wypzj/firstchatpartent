package com.study.netty.firstchat.server.course18.client.console.inter;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 控制台命令抽象类
 */
public interface ConsoleCommandInter {
    /**
     * 执行当前控制台命令对应的命令
     * @param scanner
     * @param channel
     */
    void exec(Scanner scanner, Channel channel);
}
