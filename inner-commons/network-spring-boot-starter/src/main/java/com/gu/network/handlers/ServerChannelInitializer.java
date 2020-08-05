package com.gu.network.handlers;

import com.gu.network.properties.ServerProperties;
import com.gu.network.utils.SpringUtil;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;


/**
 * @author FastG
 * @date 2019/8/26 16:34
 */
public abstract class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    private int readTimeout;

    public ServerChannelInitializer(ServerProperties serverProperties) {
        this.readTimeout = serverProperties.getOfflineTimeout();
    }

    @Autowired
    private IdleStateChecke idleStateChecke;

    @Autowired
    private MessageEncoder messageEncoder;

    @Autowired
    private LoginAuthReqHandler loginAuthReqHandler;

    @Autowired
    private MessageHandler messageHandler;

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {

        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast("idleHandler", new IdleStateHandler(this.readTimeout, 0, 0, TimeUnit.SECONDS));
        pipeline.addLast("messageDecoder", getMessageDecoder());
        pipeline.addLast("messageEncoder", messageEncoder);
        pipeline.addLast("idleStateChecke", idleStateChecke);
        //考虑验证 消息处理耗时 添加线程
        pipeline.addLast("loginAuthReqHandler", loginAuthReqHandler);
        pipeline.addLast("messageHandler", messageHandler);
        this.doChannel(pipeline);
    }


    private MessageDecoder getMessageDecoder() {
        return SpringUtil.getBean(MessageDecoder.class);
    }

    abstract protected void doChannel(ChannelPipeline channel) throws Exception;
}
