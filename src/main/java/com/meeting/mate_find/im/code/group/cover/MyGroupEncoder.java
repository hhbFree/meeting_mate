package com.meeting.mate_find.im.code.group.cover;

import com.alibaba.fastjson.JSON;
import com.meeting.mate_find.im.vo.BaseBean;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.io.Serializable;

public class MyGroupEncoder extends MessageToByteEncoder<Object> {
    protected void encode(
            ChannelHandlerContext channelHandlerContext,
            Object baseBean,
            ByteBuf byteBuf) throws Exception {

        byte[] datas = ByteUtils.getBytesFromObject(baseBean);
        byteBuf.writeBytes(datas);
        channelHandlerContext.flush();
    }
}