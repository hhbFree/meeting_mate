package com.meeting.mate_find.im.code.handler;

import com.meeting.mate_find.im.vo.MsgRecBean;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class ClientReceiveMsgHandler extends SimpleChannelInboundHandler<MsgRecBean> {
    //1.构造函数私有化，避免创建实体
    private ClientReceiveMsgHandler(){}

    //2.定义一个静态全局变量
    public static ClientReceiveMsgHandler instance=null;
    //3.获取实体方法
    public static ClientReceiveMsgHandler getInstance(){
        if(instance==null){
            synchronized (ClientReceiveMsgHandler.class){
                if(instance==null){
                    instance=new ClientReceiveMsgHandler();
                }
            }
        }
        return instance;
    }

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, MsgRecBean msgResBean) throws Exception {
        //2.接受消息
        System.out.println("fromuserid=" + msgResBean.getFromuserid() + ",msg=" + msgResBean.getMsg());
    }
}