package com.study.netty.firstchat.server.course15.constants;

import com.study.netty.firstchat.server.course15.util.Session;
import io.netty.util.AttributeKey;

/**
 * netty channel绑定的属性相关的key
 * @author 卫云鹏
 * @date in 12:32 2019/12/18
 */
public interface Attributes {
    /**
     * 登录属性
     */
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
    /**
     * Session属性
     */
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
