package com.study.netty.firstchat.server.course15.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 记录登录用户的信息
 * @author 卫云鹏
 * @date in 17:56 2020/1/5
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    private String userId;
    private String userName;
}
