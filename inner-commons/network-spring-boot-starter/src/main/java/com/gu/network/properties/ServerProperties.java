package com.gu.network.properties;

import io.netty.util.NettyRuntime;
import io.netty.util.internal.SystemPropertyUtil;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Optional;


/**
 * @author FastG
 * @date 2019/8/26 17:23
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "gu.network.server")
public class ServerProperties {


    /**
     * 监听端口
     */
    private String bindAddress = "0.0.0.0";


    /**
     * 默认监听20007端口
     */
    private Integer port = 20007;

    /**
     * 设备超时
     */
    private Integer offlineTimeout = 180;

    /**
     * 底层的socket配置参数
     */
    private SocketOptions socketOptions = new SocketOptions();

    /**
     * master线程池的线程数量
     */
    private Integer masterThreadNum = Math.max(1, SystemPropertyUtil.getInt(
            "io.netty.eventLoopThreads", NettyRuntime.availableProcessors() * 2));

    /**
     * children线程池的线程数量
     */
    private Integer childThreadNum = Math.max(1, SystemPropertyUtil.getInt(
            "io.netty.eventLoopThreads", NettyRuntime.availableProcessors() * 2));

    /**
     * socket配置参数类
     */
    @Data
    public static class SocketOptions {
        /**
         * 是否允许half connection
         */
        private Optional<Boolean>  allowHalfClosure = Optional.of(false);

        /**
         * 设置backlog的大小
         */
        private Optional<Integer>  soBacklog = Optional.of(1024);

        /**
         * 设置发送缓存大小
         */
        private Optional<Integer>   soSendBuf= Optional.of(1024);

        /**
         * 设置接收缓存大小
         */
        private Optional<Integer> soReceiveBuf = Optional.of(1024);

        /**
         * 是否采用KeepAlive，默认情况下不需要启用KeepAlive参数，一般在应用层使用心跳包保持连接，这样灵活性更高
         */
        private Optional<Boolean> soKeepAlive = Optional.of(false);

        /**
         * 是否关闭TCP的Nagle算法，拖拉机下发消息时延不敏感，可以考虑启用Nagle算法
         */
        private Optional<Boolean>  noDelay = Optional.of(false);
    }

}
