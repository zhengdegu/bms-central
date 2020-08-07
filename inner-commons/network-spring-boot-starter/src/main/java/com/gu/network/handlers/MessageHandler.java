package com.gu.network.handlers;

import com.gu.network.message.Message;
import com.gu.network.protocol.DefaultPacketMessage;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author FastG
 * @date 2019/8/28 9:19
 */
@Slf4j
@ChannelHandler.Sharable
public abstract class MessageHandler extends SimpleChannelInboundHandler<com.gu.network.message.Message.ReceivedMessage> {

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        this.doChannelInactive(ctx);
        ctx.fireChannelInactive();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        this.doExceptionCaught(ctx, cause);
        ctx.fireExceptionCaught(cause);
    }


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message.ReceivedMessage defaultPacketMessage) throws Exception {

        this.doChannelRead0(channelHandlerContext, defaultPacketMessage);
    }


    /**
     * 业务处理具体实现
     *
     * @param channelHandlerContext
     * @param defaultPacketMessage
     * @throws Exception
     */
    protected abstract void doChannelRead0(ChannelHandlerContext channelHandlerContext, Message.ReceivedMessage defaultPacketMessage) throws Exception;

    /**
     * 关闭连接操作
     * @param ctx 通道
     * @throws Exception
     */
    protected abstract void doChannelInactive(ChannelHandlerContext ctx) throws Exception;

    /**
     * 异常操作
     * @param ctx 通道
     * @param cause 异常
     * @throws Exception
     */
    protected abstract void doExceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception;
}