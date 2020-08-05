package com.gu.network.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 编码
 *
 * @author FastG
 * @date 2019/8/26 15:11
 */
@ChannelHandler.Sharable
public abstract class MessageEncoder extends MessageToByteEncoder {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object object, ByteBuf byteBuf) throws Exception {
        this.doEncode(object, byteBuf);
    }

    /**
     * 编码的具体实现
     *
     * @param o
     * @param byteBuf
     * @throws Exception
     */
    protected abstract void doEncode(Object o, ByteBuf byteBuf) throws Exception;
}
