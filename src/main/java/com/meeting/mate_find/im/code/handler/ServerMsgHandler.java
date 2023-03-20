package com.meeting.mate_find.im.code.handler;

import com.meeting.mate_find.im.vo.MsgRecBean;
import com.meeting.mate_find.im.vo.MsgReqBean;
import com.meeting.mate_find.im.vo.MsgResBean;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class ServerMsgHandler extends SimpleChannelInboundHandler<MsgReqBean> {
    //1.构造函数私有化，避免创建实体
    private ServerMsgHandler(){}

    //2.定义一个静态全局变量
    public static ServerMsgHandler instance=null;
    //3.获取实体方法
    public static ServerMsgHandler getInstance(){
        if(instance==null){
            synchronized (ServerMsgHandler.class){
                if(instance==null){
                    instance=new ServerMsgHandler();
                }
            }
        }
        return instance;
    }

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, MsgReqBean msgReqBean) throws Exception {
        //1.发送消息结果响应
        Integer touserid = msgReqBean.getTouserid();
        Channel c = Server2ChatHandler.map.get(touserid);
        if (c == null) {
            MsgResBean res = new MsgResBean();
            res.setStatus(1);
            res.setMsg(touserid + ",不在线");
            channelHandlerContext.writeAndFlush(res);
        } else {
            MsgRecBean res = new MsgRecBean();
            res.setFromuserid(msgReqBean.getFromuserid());
            res.setMsg(msgReqBean.getMsg());
            c.writeAndFlush(res);
        }
    }
}