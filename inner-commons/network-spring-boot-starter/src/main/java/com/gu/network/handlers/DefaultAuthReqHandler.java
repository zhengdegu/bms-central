package com.gu.network.handlers;

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
        log.info("You should extends com.gu.network.handlers.LoginAuthReqHandler and override the dochannelRead method to add the active channel:{}", channelHandlerContext);
        return true;
    }
}
