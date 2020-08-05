package com.gu.network.handlers;


import com.google.protobuf.ByteString;
import com.gu.network.consts.ProtocolConstant;
import com.gu.network.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.handler.codec.CorruptedFrameException;
import io.netty.handler.codec.TooLongFrameException;
import lombok.extern.slf4j.Slf4j;


/**
 * 解码具体实现类
 *
 * @author FastG
 * @date 2019/8/26 14:26
 */

@Slf4j
public class DefaultMessageDecoder extends MessageDecoder<Message.ReceivedMessage.Builder> {


    @Override
    protected Message.ReceivedMessage.Builder doDecode(ByteBuf byteBuf) throws Exception {


        //方便调试
        if (log.isDebugEnabled()) {
            log.debug("接收到的数据：{}", ByteBufUtil.hexDump(byteBuf));
        }
        if (byteBuf.readableBytes() > ProtocolConstant.MAX_FRAME_LENGTH) {
            byteBuf.skipBytes(byteBuf.readableBytes());
            throw new TooLongFrameException("Adjusted frame length [" + ProtocolConstant.MAX_FRAME_LENGTH + "]exceeds discarded");
        }
        if (byteBuf.readableBytes() < ProtocolConstant.HEADER_LENGTH) {
            return null;
        } else {
            //记录包头开始的index
            int beginReader = byteBuf.readerIndex();
            int actualLengthFieldOffset = beginReader + 9;
            //byteBuf = byteBuf.order(ByteOrder.BIG_ENDIAN);
            long frameLength = byteBuf.getUnsignedShort(actualLengthFieldOffset);
            if (frameLength < ProtocolConstant.LENGTH) {
                this.failOnNegativeLengthField(byteBuf, frameLength, ProtocolConstant.HEADER_LENGTH);
            }
            if (frameLength > ProtocolConstant.MAX_FRAME_LENGTH) {
                this.failOnNegativeLengthField(byteBuf, frameLength, byteBuf.readableBytes());
            }
            frameLength += ProtocolConstant.HEADER_LENGTH;
            if (frameLength < ProtocolConstant.HEADER_LENGTH) {
                this.failOnNegativeLengthField(byteBuf, frameLength, ProtocolConstant.HEADER_LENGTH);
            }
            int frameLengthInt = (int) frameLength;

            if (byteBuf.readableBytes() < frameLengthInt) {
                return null;
            } else {
                int readerIndex = byteBuf.readerIndex();

                ByteBuf frame = this.extractFrame(byteBuf, readerIndex, frameLengthInt);
                long drc;
                byte version;
                int messageLength;
                byte[] receivedMsg;
                try {
                    drc = frame.readLong();
                    version = (byte) frame.readUnsignedByte();
                    messageLength = frame.readUnsignedShort();
                    receivedMsg = new byte[messageLength];
                    frame.readBytes(receivedMsg);
                } finally {
                    frame.release();
                }
                byteBuf.readerIndex(readerIndex + frameLengthInt);
                return com.gu.network.message.Message.ReceivedMessage.newBuilder().setDrcId(String.valueOf(drc))
                        .setVersion(String.valueOf(version))
                        .setPayloads(ByteString.copyFrom(receivedMsg));
            }
        }
    }


    private void failOnNegativeLengthField(ByteBuf in, long frameLength, int lengthFieldEndOffset) {
        in.skipBytes(lengthFieldEndOffset);
        throw new CorruptedFrameException("negative data length field: " + frameLength);
    }

    protected ByteBuf extractFrame(ByteBuf buffer, int index, int length) {
        return buffer.retainedSlice(index, length);
    }

}