package com.gu.network.handlers;


import com.gu.network.exception.DefaultException;
import com.gu.network.protocol.DefaultPacketMessage;
import io.netty.buffer.ByteBuf;
import lombok.extern.slf4j.Slf4j;

/**
 * 默认编码实现类
 *
 * @author FastG
 * @date 2019/8/26 15:21
 */

@Slf4j
public class DefaultMessageEncoder extends MessageEncoder {


    @Override
    protected void doEncode(Object defaultPacketMessage, ByteBuf byteBuf) throws Exception {
        if (defaultPacketMessage == null) {
            throw new DefaultException("the encoder message is null");
        }

        if (log.isDebugEnabled()) {
            log.debug("[{}] receive message :{}", DefaultMessageEncoder.class.getSimpleName(), defaultPacketMessage.toString());
        }
        if (defaultPacketMessage instanceof DefaultPacketMessage) {
            DefaultPacketMessage defaultPacketMessage1 = (DefaultPacketMessage) defaultPacketMessage;
            //byteBuf.writeLong(defaultPacketMessage1.getPacketHeader().getSeriaNumber());
            //byteBuf.writeByte(defaultPacketMessage1.getPacketHeader().getVersion());
            //byteBuf.writeShort(defaultPacketMessage1.getPacketHeader().getContentLength());
            String body = defaultPacketMessage1.getBody();
            byte[] out = body.getBytes();
            byteBuf.writeBytes(out);
        }

        if (defaultPacketMessage instanceof String) {
            byte[] out = ((String) defaultPacketMessage).getBytes();
            byteBuf.writeBytes(out);
        }

    }
}
