package com.gu.network.server;


import com.gu.network.handlers.ServerChannelInitializer;
import com.gu.network.properties.ServerProperties;
import com.gu.network.utils.PlatformDependent;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.ServerChannel;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.kqueue.KQueueEventLoopGroup;
import io.netty.channel.kqueue.KQueueServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;


import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author FastG
 * @date 2019/8/26 17:52
 */
@Slf4j
public class DefaultServer implements Server {


    private ServerProperties serverProperties;

    private ServerChannelInitializer serverChannelInitializer;

    private Integer port;

    /**
     * the server attribute
     */
    private ServerAttr serverAttr;

    private EventLoopGroup masterEventLoopGroup;
    private EventLoopGroup childrenEventLoopGroup;

    private AtomicBoolean started = new AtomicBoolean(false);

    public DefaultServer(ServerProperties serverProperties, ServerChannelInitializer serverChannelInitializer) {
        this.serverProperties = serverProperties;
        this.serverChannelInitializer = serverChannelInitializer;
        this.port = serverProperties.getPort();
        this.serverAttr = ServerAttr.getLocalServer(port);
        ServerAttrHolder.put(serverAttr);
    }

    @Override
    public ServerAttr attribute() {
        return serverAttr;
    }

    @Override
    public void start() {
        if (started.compareAndSet(false, true)) {
            doStart();
        }
    }

    @Override
    public void stop() {
        if (!started.get()) {
            log.warn("Server hasn't started yet!");
            return;
        }
        masterEventLoopGroup.shutdownGracefully();
        childrenEventLoopGroup.shutdownGracefully();
    }

    private void doStart() {

        Integer masterThreadNum = serverProperties.getMasterThreadNum();
        Integer childrenThreadNum = serverProperties.getChildThreadNum();
        Class<? extends ServerChannel> channelClass = null;
        PlatformDependent.Platform platform = PlatformDependent.getPlatform();
        switch (platform) {
            case LINUX:
                channelClass = EpollServerSocketChannel.class;
                this.masterEventLoopGroup = new EpollEventLoopGroup(masterThreadNum);
                this.childrenEventLoopGroup = new EpollEventLoopGroup(childrenThreadNum);
                break;
            case BSD:
                channelClass = KQueueServerSocketChannel.class;
                this.masterEventLoopGroup = new KQueueEventLoopGroup(masterThreadNum);
                this.childrenEventLoopGroup = new KQueueEventLoopGroup(childrenThreadNum);
                break;
            default:
                channelClass = NioServerSocketChannel.class;
                this.masterEventLoopGroup = new NioEventLoopGroup(masterThreadNum);
                this.childrenEventLoopGroup = new NioEventLoopGroup(childrenThreadNum);
                break;
        }

        ServerBootstrap serverBootstrap = new ServerBootstrap();


        /**
         * 配置Server和 socket的选项
         */
        ServerProperties.SocketOptions options = serverProperties.getSocketOptions();

        // backlog
        if (options.getSO_BACKLOG().isPresent()) {
            serverBootstrap.option(ChannelOption.SO_BACKLOG, options.getSO_BACKLOG().get());
        }

        if (options.getALLOW_HALF_CLOSURE().isPresent()) {
            serverBootstrap.childOption(ChannelOption.ALLOW_HALF_CLOSURE, options.getALLOW_HALF_CLOSURE().get());
        }

        if (options.getSO_SNDBUF().isPresent()) {
            serverBootstrap.childOption(ChannelOption.SO_SNDBUF, options.getSO_SNDBUF().get());
        }

        if (options.getSO_RCVBUF().isPresent()) {
            serverBootstrap.childOption(ChannelOption.SO_RCVBUF, options.getSO_RCVBUF().get());
        }

        if (options.getSO_KEEPALIVE().isPresent()) {
            serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, options.getSO_KEEPALIVE().get());
        }

        if (options.getTCP_NODELAY().isPresent()) {
            serverBootstrap.childOption(ChannelOption.TCP_NODELAY, options.getTCP_NODELAY().get());
        }
        serverBootstrap.group(masterEventLoopGroup, childrenEventLoopGroup)
                .channel(channelClass)
                .childHandler(serverChannelInitializer);
        ChannelFuture future = serverBootstrap.bind(port);
        future.addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {
                    log.info("[{}] Startup at port:{}", DefaultServer.class.getSimpleName(), port);
                }
            }
        });
    }
}
