package com.gu.network.server;

/**
 * @author FastG
 * @date 2019/8/26 17:49
 */
public interface Server {

    /**
     * 返回当前服务的attri
     *
     * @return
     */
    ServerAttr attribute();

    /**
     * 开始服务
     */
    void start();

    /**
     * 停止服务
     */
    void stop();

}
