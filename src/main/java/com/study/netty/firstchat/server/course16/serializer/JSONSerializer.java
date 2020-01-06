package com.study.netty.firstchat.server.course16.serializer;

import com.alibaba.fastjson.JSON;
import com.study.netty.firstchat.server.course16.common.SerializeConstants;
import com.study.netty.firstchat.server.course16.serializer.inter.Serializer;

/**
 * @author 卫云鹏
 * @date in 18:54 2020/1/6
 */
public class JSONSerializer implements Serializer {
    @Override
    public byte[] java2bytes(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T bytes2java(byte[] bytes, Class<T> targetType) {
        return JSON.parseObject(bytes, targetType);
    }

    @Override
    public byte getSerializerType() {
        return SerializeConstants.JSON_SERIALIZER;
    }
}
