package com.gu.network.protocol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * * 数据包格式
 * *  +——-——+——---——+——----——+
 * *  | 序列号 | 协议版本 | 消息体长度 |
 * *  +——-——+——---——+—-----——+
 * *
 *
 * @author FastG
 * @date 2019/8/26 14:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public final class DefaultPacketHeader implements java.io.Serializable {

    /**
     * 序列号
     */
    private long seriaNumber;
    /**
     * 协议版本
     */
    private byte version;
    /**
     * 消息体长度
     */
    private short contentLength;
}
