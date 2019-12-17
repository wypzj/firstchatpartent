package com.study.netty.firstchat.server.smalldemo.nettyapitest;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 卫云鹏
 * @date in 20:47 2019/12/16
 */
@Slf4j
public class ByteBufTest {
    public static void main(String[] args) {
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(9,100);
        log.info("allocator bytebuf(9,100):{}",buffer.toString());
        //write方法写入大小为4的bytes数组，改变写指针，写入大小小于初始化大小initialCapacity
        buffer.writeBytes(new byte[]{1,2,3,4});
        printInfo("writeBytes(new byte[]{1,2,3,4})",buffer);
        //write方法写入int类型，未到达initialCapacity时能够写入
        buffer.writeInt(2);
        printInfo("buffer.writeInt(2)",buffer);
        //write方法改变写指针，指针位置在initialCapacity时，无法写入
        buffer.writeBytes(new byte[]{2});
        printInfo("buffer.writeBytes(new byte[]{2})",buffer);
        //write方法改变写指针，写入过程中发现无法写入时会自动扩容
        buffer.writeBytes(new byte[]{3});
        printInfo("buffer.writeBytes(new byte[]{3})",buffer);
        //get方法不会改变读指针
        log.info("==============get方法===========");
        log.info("buffer.getByte(0)={}",buffer.getByte(0));
        log.info("buffer.getInt(1)={}",buffer.getInt(1));
        log.info("buffer.getShort(2)={}",buffer.getShort(2));
        printInfo("buffer.getByte()",buffer);
        //set方法不会改变写指针的位置
        log.info("============set==========");
        byte aByte = buffer.getByte(0);
        buffer.setByte(0,2);
        byte bByte = buffer.getByte(0);
        log.info("buffer.setByte(0,2):before:{},after:{}",aByte,bByte);
        printInfo("buffer.setByte(0,2)",buffer);

        //read方法改变buf的读指针
        byte[] bytes = new byte[buffer.readableBytes()];
        ByteBuf byteBuf = buffer.readBytes(bytes);
        printInfo("buffer.readBytes("+bytes.length+")",buffer);
    }
    private static void printInfo(String action,ByteBuf buffer){
        log.info("==========after {} ==========",action);
        log.info("buffer.capacity()[当前buf分配的内存大小]={}",buffer.capacity());
        log.info("buffer.maxCapacity[当前buf最大可分配内存大小]()={}",buffer.maxCapacity());
        log.info("buffer.readerIndex()[当前buf可读指针下标]={}",buffer.readerIndex());
        log.info("buffer.readableBytes()[当前buf剩余可读区间大小]={}",buffer.readableBytes());
        log.info("buffer.isReadable()[当前buf是否可读]={}",buffer.isReadable());
        log.info("buffer.writerIndex()[当前buf写指针位置]={}",buffer.writerIndex());
        log.info("buffer.writableBytes()[当前buf剩余可写区间大小]={}",buffer.writableBytes());
        log.info("buffer.isWritable()[当前buf是否可写]={}",buffer.isWritable());
        log.info("buffer.maxWritableBytes()[当前最大剩余可写区间]={}",buffer.maxWritableBytes());
    }

}
