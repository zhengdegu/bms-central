package com.gu.network.handlers;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author FastG
 * @date 2019/8/26 19:14
 */
@Slf4j
public class DefaultIdleStateChecke extends IdleStateChecke {

    @Override
    protected void doUserEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {


        IdleStateEvent evtD = (IdleStateEvent) evt;
        if (evtD.state() == IdleState.READER_IDLE) {
            log.warn("[{}] Hasn't read data, connect will close the channel:{}", DefaultIdleStateChecke.class.getSimpleName(), ctx.channel());
            ctx.channel().close();
        }else if (evtD.state() == IdleState.WRITER_IDLE){
            log.warn("[{}] Hasn't write data timeout,the channel:{}", DefaultIdleStateChecke.class.getSimpleName(), ctx.channel());
        }
    }
}
