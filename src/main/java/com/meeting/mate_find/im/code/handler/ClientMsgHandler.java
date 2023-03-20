package com.meeting.mate_find.im.code.handler;

import com.meeting.mate_find.im.vo.MsgResBean;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class ClientMsgHandler extends SimpleChannelInboundHandler<MsgResBean> {
    //1.构造函数私有化，避免创建实体
    private ClientMsgHandler(){}

    //2.定义一个静态全局变量
    public static ClientMsgHandler instance=null;
    //3.获取实体方法
    public static ClientMsgHandler getInstance(){
        if(instance==null){
            synchronized (ClientMsgHandler.class){
                if(instance==null){
                    instance=new ClientMsgHandler();
                }
            }
        }
        return instance;
    }

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, MsgResBean msgResBean) throws Exception {
        //1.发送消息结果响应
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>发送响应：" + msgResBean.getMsg());
    }
}