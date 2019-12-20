package com.study.netty.firstchat.server.sendmessagedemo.constants;

import io.netty.util.AttributeKey;

/**
 * netty channel绑定的属性相关的key
 * @author 卫云鹏
 * @date in 12:32 2019/12/18
 */
public interface Attributes {
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
