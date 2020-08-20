package com.gu.admin.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author FastG
 * @date 2019/12/30 16:42
 */
@Data
@ConfigurationProperties(prefix = "spring.boot.admin.notify.server")
public class SelfNotifierProperties {
    private boolean enabled = false;
    private String webHookToken;
}
