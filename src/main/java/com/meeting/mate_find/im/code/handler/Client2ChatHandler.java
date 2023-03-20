package com.meeting.mate_find.im.code.handler;

import com.meeting.mate_find.im.vo.*;
import io.netty.channel.*;
import io.netty.util.AttributeKey;

import java.util.Scanner;
@ChannelHandler.Sharable
public class Client2ChatHandler extends SimpleChannelInboundHandler<LoginResBean> {

    //1.构造函数私有化，避免创建实体
    private Client2ChatHandler(){}
    //2.定义一个静态全局变量
    public static Client2ChatHandler instance=null;
    //3.获取实体方法
    public static Client2ChatHandler getInstance(){
        if(instance==null){
            synchronized (Client2ChatHandler.class){
                if(instance==null){
                    instance=new Client2ChatHandler();
                }
            }
        }
        return instance;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //通道就绪时，发起登录请求
        login(ctx.channel());
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, LoginResBean res) throws Exception {
        //1.登录结果响应
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>登录响应：" + res.getMsg());
        if (res.getStatus() == 0) {
            //1.登录成功，则给通道绑定属性
            ctx.channel().attr(AttributeKey.valueOf("userid")).set(res.getUserid());
            //2.调用发送消息方法
            sendMsg(ctx.channel());
        } else {
            //1.登录失败，调用登录方法
            login(ctx.channel());
        }
    }

    //登录方法
    private void login(Channel channel) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(">>用户ID：");
        Integer userid = scanner.nextInt();
        System.out.println(">>用户名称：");
        String username = scanner.next();

        LoginReqBean bean = new LoginReqBean();
        bean.setUserid(userid);
        bean.setUsername(username);
        channel.writeAndFlush(bean);
    }

    //发送消息方法
    private void sendMsg(final Channel channel) {
        final Scanner scanner = new Scanner(System.in);
        new Thread(new Runnable() {


            public void run() {
                while (true) {
                    System.out.println(">>接收人ID：");
                    Integer touserid = scanner.nextInt();
                    System.out.println(">>聊天内容：");
                    String msg = scanner.next();

                    MsgReqBean bean = new MsgReqBean();
                    //从通道属性获取发送人ID
                    Integer fromuserid = (Integer) channel.attr(AttributeKey.valueOf("userid")).get();
                    //发送人ID
                    bean.setFromuserid(fromuserid);
                    //接受人ID
                    bean.setTouserid(touserid);
                    //发送消息
                    bean.setMsg(msg);
                    channel.writeAndFlush(bean);
                }
            }

        }).start();
    }
}