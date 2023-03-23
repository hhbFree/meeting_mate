package com.meeting.mate_find.im.code.web;
 
import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lw
 * @since 2021/8/20
 */
//TextWebSocketFrame表示一个文本帧
@ChannelHandler.Sharable
public class MyTextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    //当web客户端连接后触发该方法
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerAdded 被调用 "+ctx.channel().id().asLongText());
        System.out.println("handlerAdded 被调用 "+ctx.channel().id().asShortText());
    }
 
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //id表示唯一 longText是唯一的 shortText不是惟一的
        System.out.println("handlerRemoved 被调用 "+ctx.channel().id().asLongText());
        System.out.println("handlerRemoved 被调用 "+ctx.channel().id().asShortText());
    }
 
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("异常发生 "+cause.getMessage());
        //关闭连接
        ctx.close();
    }

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        System.out.println("服务端接收到消息"+textWebSocketFrame.text());
        //回复消息
        channelHandlerContext.channel().writeAndFlush(new TextWebSocketFrame("服务器时间 "+ LocalDateTime.now()+" "+textWebSocketFrame.text()));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //首次连接是FullHttpRequest，处理参数 by zhengkai.blog.csdn.net
        if (null != msg && msg instanceof FullHttpRequest) {
            FullHttpRequest request = (FullHttpRequest) msg;
            String uri = request.getUri();

            Map paramMap=getUrlParams(uri);
            System.out.println("接收到的参数是："+ JSON.toJSONString(paramMap));
            //如果url包含参数，需要处理
            if(uri.contains("?")){
                String newUri=uri.substring(0,uri.indexOf("?"));
                System.out.println(newUri);
                request.setUri(newUri);
            }

        }
        super.channelRead(ctx, msg);
    }

    private static Map getUrlParams(String url){
        Map<String,String> map = new HashMap<>();
        url = url.replace("?",";");
        if (!url.contains(";")){
            return map;
        }
        if (url.split(";").length > 0){
            String[] arr = url.split(";")[1].split("&");
            for (String s : arr){
                String key = s.split("=")[0];
                String value = s.split("=")[1];
                map.put(key,value);
            }
            return  map;

        }else{
            return map;
        }
    }
}