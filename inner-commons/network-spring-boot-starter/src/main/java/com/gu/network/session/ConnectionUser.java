package com.gu.network.session;

import io.netty.channel.Channel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author g130016
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConnectionUser {
    private String drcId;
    private Channel channel;
}
