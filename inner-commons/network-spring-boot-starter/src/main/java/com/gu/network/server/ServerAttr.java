package com.gu.network.server;


import com.gu.network.utils.NetUti;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author FastG
 * @date 2019/8/26 17:46
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServerAttr {

    private String address;
    private int port;

    public static ServerAttr getLocalServer(int serverPort) {
        return ServerAttr.builder()
                .address(NetUti.getLocalIpV4())
                .port(serverPort)
                .build();
    }

}
