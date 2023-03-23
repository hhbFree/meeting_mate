package com.meeting.mate_find.im.code.web;
 
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
 
/**
 * @author lw
 * @since 2021/8/20
 */
public class MyServer {
    public static void main(String[] args) throws InterruptedException {
        //创建2个线程组
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            //因为基于http协议 使用http的编码器和解码器
                            pipeline.addLast(new HttpServerCodec())
                                    //以块方式写 添加ChunkedWriteHandler处理器
                                    .addLast(new ChunkedWriteHandler())
                                    //http传输过程中会分段 HttpObjectAggregator可以将多个段聚合
                                    //这就是为什么，当浏览器发送大量数据时，就会发多次http请求
                                    .addLast(new HttpObjectAggregator(8192))
                                    //对应websocket 它的数据是以帧(WebSocketFrame)形式传递
                                    //浏览器请求时ws://localhost:7000/hello表示请求的uri
                                    //WebSocketServerProtocolHandler的核心功能是将http协议升级为ws协议 并保持长连接
                                    //是通过一个状态码 101
                                    .addLast(new MyTextWebSocketFrameHandler())
                                    .addLast(new WebSocketServerProtocolHandler("/chat"));
                                    //自定义的handler做业务逻辑处理
                        }
                    });
            //启动服务器
            ChannelFuture channelFuture = serverBootstrap.bind(8800).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}