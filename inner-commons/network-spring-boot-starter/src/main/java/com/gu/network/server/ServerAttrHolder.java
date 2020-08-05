package com.gu.network.server;

/**
 * @author FastG
 * @date 2019/8/30 17:48
 */
public class ServerAttrHolder {


    private static ServerAttr serverAttr;

    public static void put(ServerAttr serverAttr) {
        ServerAttrHolder.serverAttr = serverAttr;
    }

    public static ServerAttr get() {
        return ServerAttrHolder.serverAttr;
    }
}
