package com.meeting.mate_find.im.code.handler;

import com.meeting.mate_find.im.vo.*;
import io.netty.channel.*;
import io.netty.util.AttributeKey;

import java.util.HashMap;
import java.util.Map;
@ChannelHandler.Sharable
public class Server2ChatHandler extends SimpleChannelInboundHandler<LoginReqBean> {
    //1.定义一个Map（key是用户ID，value是连接通道）
    public static Map<Integer, Channel> map = new HashMap<Integer, Channel>();


    //1.构造函数私有化，避免创建实体
    private Server2ChatHandler(){}

    //2.定义一个静态全局变量
    public static Server2ChatHandler instance=null;
    //3.获取实体方法
    public static Server2ChatHandler getInstance(){
        if(instance==null){
            synchronized (Server2ChatHandler.class){
                if(instance==null){
                    instance=new Server2ChatHandler();
                }
            }
        }
        return instance;
    }


    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, LoginReqBean loginReqBean) throws Exception {
        //1.登录请求
        login(loginReqBean, channelHandlerContext.channel());
    }

    //登录处理方法
    private void login(LoginReqBean bean, Channel channel) {
        LoginResBean res = new LoginResBean();
        //从map里面根据用户ID获取连接通道
        Channel c = map.get(bean.getUserid());
        if (c == null) {
            //通道为空，证明该用户没有在线
            //1.添加到map
            map.put(bean.getUserid(), channel);
            //2.给通道赋值
            channel.attr(AttributeKey.valueOf("userid")).set(bean.getUserid());
            //3.响应
            res.setStatus(0);
            res.setMsg("登录成功");
            res.setUserid(bean.getUserid());
            channel.writeAndFlush(res);
        } else {
            //通道不为空，证明该用户已经在线了
            res.setStatus(1);
            res.setMsg("该账户目前在线");
            channel.writeAndFlush(res);
        }
    }


}
