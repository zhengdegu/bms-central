package com.gu.network.handlers;


import com.gu.network.properties.ServerProperties;
import io.netty.channel.ChannelPipeline;

/**
 * @author FastG
 * @date 2019/8/26 16:39
 */
public class DefaultServerChannelInitialize extends ServerChannelInitializer {


    public DefaultServerChannelInitialize(ServerProperties serverProperties) {
        super(serverProperties);
    }

    @Override
    protected void doChannel(ChannelPipeline channel) throws Exception {
    }
}
