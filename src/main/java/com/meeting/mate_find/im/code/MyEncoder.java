package com.meeting.mate_find.im.code;

import com.alibaba.fastjson.JSON;
import com.meeting.mate_find.im.vo.BaseBean;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MyEncoder extends MessageToByteEncoder<BaseBean> {
    protected void encode(
            ChannelHandlerContext channelHandlerContext,
            BaseBean baseBean,
            ByteBuf byteBuf) throws Exception {

//1.把实体序列化成字节数字
        byte[] bytes = JSON.toJSONBytes(baseBean);

//2.根据协议组装数据
        byteBuf.writeInt(baseBean.getTag());//标识（4个字节）
        byteBuf.writeByte(baseBean.code());//指令（1个字节）
        byteBuf.writeInt(bytes.length);//长度（4个字节）
        byteBuf.writeBytes(bytes);//
    }
}