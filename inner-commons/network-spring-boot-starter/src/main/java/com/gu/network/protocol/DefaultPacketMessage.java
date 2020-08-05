package com.gu.network.protocol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * @author FastG
 * @date 2019/8/26 14:39
 */

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DefaultPacketMessage implements java.io.Serializable {
    /**
     * 消息体
     */
    private String body;

    /**
     * 消息头
     */
    private DefaultPacketHeader packetHeader;

}
