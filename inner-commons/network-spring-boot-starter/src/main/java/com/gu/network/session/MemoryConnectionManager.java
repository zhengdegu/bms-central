package com.gu.network.session;


import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author FastG
 * @date 2019/8/28 17:49
 */
@Slf4j
public class MemoryConnectionManager implements ConnectionManager {


    private static final Map<String, Connection> CONNECTION_HASH_MAP = new ConcurrentHashMap<>();

    public MemoryConnectionManager() {
        super();
    }

    @Override
    public void add(Channel channel, String drcId, String serverAddress, Integer serverPort) {
        if (drcId == null || channel == null) {
            return;
        }
        Connection connection = Connection.builder()
                .drcId(drcId)
                .channelId(channel.id().asLongText())
                .channel(channel)
                .serverAddress(serverAddress)
                .serverPort(serverPort)
                .build();

        CONNECTION_HASH_MAP.put(drcId, connection);

        ConnectionUtil.markOnline(channel);
    }

    @Override
    public void update(Channel channel, String drcId, String serverAddress, Integer serverPort) {
        if (drcId == null || channel == null) {
            return;
        }
        final String channelId = channel.id().asLongText();

        Connection connection = get(drcId);

        if (connection == null) {
            return;
        }
        final String channelIdO = connection.getChannelId();

        if (!org.apache.commons.lang3.StringUtils.equals(channelId, channelIdO)) {
            Connection connectionN = Connection.builder()
                    .channelId(channelId)
                    .channel(channel)
                    .drcId(drcId)
                    .serverAddress(serverAddress)
                    .serverPort(serverPort)
                    .build();

            CONNECTION_HASH_MAP.put(drcId, connectionN);
            ConnectionUtil.markOnline(channel);
        }
    }

    @Override
    public void remove(String drcId, Channel channel) {
        Connection connection = this.get(drcId);
        if (connection != null && org.apache.commons.lang3.StringUtils.equals(connection.getChannelId(), channel.id().asLongText())) {
            CONNECTION_HASH_MAP.remove(drcId);
        }
    }

    @Override
    public Connection get(String drcId) {
        if (drcId == null) {
            return null;
        }
        return CONNECTION_HASH_MAP.get(drcId);
    }

    @Override
    public boolean contains(String drcId) {
        return CONNECTION_HASH_MAP.get(drcId) != null;
    }

    @Override
    public List<ConnectionUser> onlineUser() {
        return CONNECTION_HASH_MAP.values().stream()
                .map(connection -> {
                    return ConnectionUser.builder().drcId(connection.getDrcId()).channel(connection.getChannel()).build();
                }).distinct().collect(Collectors.toList());
    }


    @Override
    public void sendMessageToClient(String drcId, Object memessage) {

        if (drcId == null || memessage == null) {
            log.error("send to drcId:{} message is null", drcId);
            return;
        }

        Connection connection = get(drcId);


        if (connection != null) {
            //noinspection unchecked
            connection.getChannel().writeAndFlush(memessage).addListeners(future -> {
                if (future.isSuccess()) {
                    log.info("send message to drcId:{} successed !", drcId);
                }
                //考虑重试次数
            });
        } else {
            log.info("drcId:{} is  offline!", drcId);
        }
    }

    @Override
    public boolean closeConnection(String drcId) {
        Connection connection = get(drcId);
        if (connection != null) {
            connection.getChannel().close();
            return true;
        } else {
            log.info("drcId:{} is  offline!", drcId);
            return false;
        }
    }
}
