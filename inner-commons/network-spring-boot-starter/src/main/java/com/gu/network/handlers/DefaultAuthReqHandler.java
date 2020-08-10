package com.gu.network.handlers;

import com.google.protobuf.ByteString;
import com.gu.network.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * 默认登录校验   不做任何人处理
 *
 * @author FastG
 * @date 2019/8/28 9:18
 */
@Slf4j
public class DefaultAuthReqHandler extends AuthReqHandler {
    @Override
    protected boolean doChannelRead(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        if (o instanceof Message.ReceivedMessage) {
            byte[] bytes = ((Message.ReceivedMessage) o).getMessage().toByteArray();
            // 协议  8字节(IoT终端序列号)+ 1字节(协议版本号)+ 2字节（消息体长度）+n字节(消息体)
            ByteBuf buffer = Unpooled.buffer(bytes.length);
            try {
                buffer.writeBytes(bytes);
                //解析终端序列号进行终端合法性校验，同时添加本地session和分布式session
                long serNumber = buffer.readLong();
                short version = buffer.readUnsignedByte();
                int length = buffer.readUnsignedShort();
            }finally {
                buffer.release();
            }
            log.info("You should extends com.gu.network.handlers.LoginAuthReqHandler and override the dochannelRead method to add the active channel:{}", channelHandlerContext);
            return true;
        }
        return  false;
    }
}
