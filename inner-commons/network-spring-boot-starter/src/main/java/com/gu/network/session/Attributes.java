package com.gu.network.session;

import io.netty.util.AttributeKey;

public interface Attributes {
    /**
     * 在线 attri
     */
    AttributeKey<Boolean> ON_LINE = AttributeKey.newInstance("online");

    /**
     * 终端 attr
     */
    AttributeKey<String> CLIENT_ID = AttributeKey.newInstance("clientId");

}
