package com.gu.network.session;

import io.netty.channel.Channel;

/**
 * @author g130016
 */
public class ConnectionUtil {

    private ConnectionUtil() {

    }

    public static void markOnline(Channel channel) {
        channel.attr(Attributes.ON_LINE).set(true);
    }

    public static void markOffline(Channel channel) {
        channel.attr(Attributes.ON_LINE).set(null);
    }

    public static boolean hasLogin(Channel channel) {
        return channel.hasAttr(Attributes.ON_LINE) && channel.attr(Attributes.ON_LINE).get();
    }

    public static void markClientId(Channel channel, String drcId) {
        channel.attr(Attributes.CLIENT_ID).set(drcId);
    }

    public static String getClientId(Channel channel) {
        if (!channel.hasAttr(Attributes.CLIENT_ID)) {
            return null;
        }
        return channel.attr(Attributes.CLIENT_ID).get();
    }

    public static void markClientIdOff(Channel channel) {
        channel.attr(Attributes.CLIENT_ID).set(null);
    }
}
