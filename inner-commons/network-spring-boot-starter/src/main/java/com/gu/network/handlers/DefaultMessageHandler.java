package com.gu.network.handlers;

import com.gu.network.message.Message;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * 消息处理具体实现类   默认不做处理
 *
 * @author FastG
 * @date 2019/8/28 9:23
 */
@Slf4j
public class DefaultMessageHandler extends MessageHandler {
    @Override
    protected void doChannelRead0(ChannelHandlerContext channelHandlerContext, Message.ReceivedMessage defaultPacketMessage) throws Exception {
        log.info("You should extends com.gu.network.handlers.MessageHandler and override the doChannelRead0 method to add the active channel:{}", channelHandlerContext);
    }

    @Override
    protected void doChannelInactiv(ChannelHandlerContext ctx) throws Exception {
        log.info("You should extends com.gu.network.handlers.MessageHandler and override doChannelInactivo method to add the active channel:{}", ctx);
    }

    @Override
    protected void doExceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("You should extends com.gu.network.handlers.MessageHandler and override doExceptionCaught method to add the active channel:{}", ctx);
    }

}
