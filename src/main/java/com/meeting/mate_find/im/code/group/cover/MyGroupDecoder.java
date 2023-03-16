package com.meeting.mate_find.im.code.group.cover;

import com.alibaba.fastjson.JSON;
import com.meeting.mate_find.im.code.MapUtils;
import com.meeting.mate_find.im.vo.BaseBean;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MyGroupDecoder extends ByteToMessageDecoder {
    protected void decode(
            ChannelHandlerContext channelHandlerContext,
            ByteBuf byteBuf,
            List<Object> list) throws Exception {

        byte[] req = new byte[byteBuf.readableBytes()];

        Object obj = ByteUtils.deserialize(req);
        list.add(obj);
    }
}