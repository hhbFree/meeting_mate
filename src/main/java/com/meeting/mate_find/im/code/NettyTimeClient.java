package com.meeting.mate_find.im.code;

import com.meeting.mate_find.im.code.handler.Client2ChatHandler;
import com.meeting.mate_find.im.code.handler.ClientChatHandler;
import com.meeting.mate_find.im.code.handler.ClientMsgHandler;
import com.meeting.mate_find.im.code.handler.ClientReceiveMsgHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class NettyTimeClient {

    public void connect(String ip, int port) {

        EventLoopGroup e = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(e).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {
                            //1.拆包器
                            ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 5, 4));
                            //2.自定义解码器
                            ch.pipeline().addLast(new MyDecoder());
                            //3.自定义业务 '
                            ch.pipeline().addLast(Client2ChatHandler.getInstance());
                            ch.pipeline().addLast(ClientMsgHandler.getInstance());
                            ch.pipeline().addLast(ClientReceiveMsgHandler.getInstance());
                            // 4.自定义编码器
                            ch.pipeline().addLast(new MyEncoder());
                        }
                    });

            //发起异步链接操作
            ChannelFuture sync = bootstrap.connect(ip, port).sync();
            //等待客户端链接关闭
            sync.channel().closeFuture().sync();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        } finally {
            e.shutdownGracefully();
        }


    }

    public static void main(String[] args) {
        int port = 8081;

        new NettyTimeClient().connect("127.0.0.1", port);
    }
}
