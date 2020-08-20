package com.gu.admin.config;

import com.gu.admin.notifiler.SelfNotifier;
import com.gu.admin.properties.SelfNotifierProperties;
import de.codecentric.boot.admin.server.config.AdminServerHazelcastAutoConfiguration;
import de.codecentric.boot.admin.server.config.AdminServerNotifierAutoConfiguration;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author FastG
 * @date 2019/12/30 16:23
 */
@Configuration
@ConditionalOnProperty(prefix = "spring.boot.admin.notify.server", name = "enabled", havingValue = "true")
@AutoConfigureBefore({AdminServerHazelcastAutoConfiguration.NotifierTriggerConfiguration.class,
        AdminServerNotifierAutoConfiguration.CompositeNotifierConfiguration.class})
public class NotifierConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public SelfNotifier selfNotifier(InstanceRepository repository, SelfNotifierProperties selfNotifierProperties) {
        return new SelfNotifier(repository, selfNotifierProperties);
    }
}
