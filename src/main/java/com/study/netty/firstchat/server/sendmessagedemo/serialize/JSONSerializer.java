package com.study.netty.firstchat.server.sendmessagedemo.serialize;

import com.alibaba.fastjson.JSON;
import com.study.netty.firstchat.server.sendmessagedemo.constants.SerializerAlgorithm;
import com.study.netty.firstchat.server.sendmessagedemo.serialize.inter.Serializer;

/**
 * JSON类型的序列化算法
 * @author 卫云鹏
 * @date in 15:47 2019/12/17
 */
public class JSONSerializer implements Serializer {
    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> target, byte[] bytes) {
        return JSON.parseObject(bytes,target);
    }
}
