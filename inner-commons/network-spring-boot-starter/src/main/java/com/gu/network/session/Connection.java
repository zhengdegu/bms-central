package com.gu.network.session;

import io.netty.channel.Channel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Connection {


    private String drcId;

    private String channelId;

    private Channel channel;

    private String serverAddress;

    private Integer serverPort;

}
