package com.gu.network.handlers;

import com.gu.network.exception.DefaultException;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author FastG
 * @date 2019/8/26 19:37
 */
@ChannelHandler.Sharable
public abstract class AuthReqHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

        if (o == null) {
            throw new DefaultException("[" + AuthReqHandler.class.getSimpleName() + "] Hasn't read data ");
        }

        if (this.doChannelRead(channelHandlerContext, o)) {

            channelHandlerContext.fireChannelRead(o);
            //校验成功 移除
            channelHandlerContext.pipeline().remove(AuthReqHandler.class);
        }
    }

    /**
     * 登录校验的具体实现
     *
     * @param channelHandlerContext
     * @param o
     * @throws Exception
     */
    abstract protected boolean doChannelRead(ChannelHandlerContext channelHandlerContext, Object o) throws Exception;


}
