package com.meeting.mate_find.im.code;

import com.meeting.mate_find.im.code.handler.Server2ChatHandler;
import com.meeting.mate_find.im.code.handler.ServerChatHandler;
import com.meeting.mate_find.im.code.handler.ServerMsgHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class NettyTimeServer {

    public void bind(int port) {
        EventLoopGroup boss = new NioEventLoopGroup();

        EventLoopGroup work = new NioEventLoopGroup();


        try {
            ServerBootstrap b = new ServerBootstrap();

            b.group(boss, work)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        protected void initChannel(NioSocketChannel ch) {
                            //1.拆包器
                            ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 5, 4));
                            //2.自定义解码器
                            ch.pipeline().addLast(new MyDecoder());
                            //3.业务Handler
                            ch.pipeline().addLast(Server2ChatHandler.getInstance());
                            ch.pipeline().addLast(ServerMsgHandler.getInstance());
                            //4.自定义编码器
                            ch.pipeline().addLast(new MyEncoder());
                        }
                    });

            ChannelFuture sync = b.bind(port).sync();
            sync.channel().closeFuture().sync();
        } catch (
                InterruptedException e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }

    }

    public static void main(String[] args) {
        int port = 8081;

        new NettyTimeServer().bind(port);
    }
}
