package cn.edu.xmut.learningplatform.websoket;

import cn.edu.xmut.learningplatform.model.sign;
import cn.edu.xmut.learningplatform.service.signService;
import com.google.gson.Gson;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@ChannelHandler.Sharable
public class WebSocketHandler extends SimpleChannelInboundHandler<WebSocketFrame> {
    private signService signService;
    public WebSocketHandler(ApplicationContext context) {
        this.signService = context.getBean(signService.class);
    }
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 当有新的WebSocket连接建立时添加到channelGroup中
        channelGroup.add(ctx.channel());
        System.out.println("New WebSocket connection: " + ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // 当WebSocket连接关闭时从channelGroup中移除
        channelGroup.remove(ctx.channel());
        System.out.println("WebSocket connection closed: " + ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame frame) throws Exception {
        if (frame instanceof TextWebSocketFrame) {
            // Handle WebSocket messages from the client
            String request = ((TextWebSocketFrame) frame).text();
            sign signBySignId = signService.getSignBySignId(Integer.parseInt(request));
            channelGroup.writeAndFlush(new TextWebSocketFrame(new Gson().toJson(signBySignId)));
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 处理异常情况
        cause.printStackTrace();
        ctx.close();
    }
}


